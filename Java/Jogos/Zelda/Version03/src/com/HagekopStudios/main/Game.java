package com.HagekopStudios.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.HagekopStudios.gfx.Screen;
import com.HagekopStudios.gfx.SpriteSheet;
import com.HagekopStudios.screen.Menu;
import com.HagekopStudios.screen.TileMenu;

public class Game extends Canvas implements Runnable, GameConfig {

	/**
	 * 
	 */
	public static String GameState = "STARTING";

	public static int CUR_LEVEL = 1;
	public static boolean running = false;
	private static final long serialVersionUID = 3L;
	private BufferedImage image = new BufferedImage(GameConfig.WIDTH, GameConfig.HEIGTH, BufferedImage.TYPE_INT_RGB);
	private int[] colors = new int[256];
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private Screen screen;
	private Screen lightScreen;
	private int playerDeadTime;
	private int pendingLevelChange;
	private int wonTimer = 0;
	private int tickCount = 0;
	private int currentLevel = 3;
	private int gameTime = 0;
	public boolean hasWon = false;
	public Menu menu;
	private GetsInput input = new GetsInput(this);

	public void setMenu(Menu menu) {
		this.menu = menu;
		if (menu != null) {
			menu.init(this, input);
		}
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	private void init() {
		int pp = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					int mid = (rr * 30 + gg * 59 + bb * 11) / 100;

					int r1 = ((rr + mid * 1) / 2) * 230 / 255 + 10;
					int g1 = ((gg + mid * 1) / 2) * 230 / 255 + 10;
					int b1 = ((bb + mid * 1) / 2) * 230 / 255 + 10;

					colors[pp++] = r1 << 16 | g1 << 8 | b1;
				}
			}
		}
		try {
			screen = new Screen(GameConfig.WIDTH, GameConfig.HEIGTH,
					new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/sprite.png"))));
			lightScreen = new Screen(GameConfig.WIDTH, GameConfig.HEIGTH,
					new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("/sprite.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setMenu(new TileMenu());
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTime1 = System.currentTimeMillis();

		init();

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}
			if (System.currentTimeMillis() - lastTime1 > 1000) {
				lastTime1 += 1000;
				System.out.println("Ticks: " + ticks);
				System.out.println("FPS:   " + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		tickCount++;
		if (!hasFocus()) {
			input.releaseAll();
		} else {

		}
	}

	public void render() {
	}

	public static void main(String[] args0) {
		Game game = new Game();
		game.setMinimumSize(new Dimension(GameConfig.WIDTH*GameConfig.SCALE,GameConfig.HEIGTH*GameConfig.SCALE));
		game.setMaximumSize(new Dimension(GameConfig.WIDTH*GameConfig.SCALE,GameConfig.HEIGTH*GameConfig.SCALE));
		game.setPreferredSize(new Dimension(GameConfig.WIDTH*GameConfig.SCALE,GameConfig.HEIGTH*GameConfig.SCALE));
		
		JFrame frame = new JFrame(Game.Name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}
