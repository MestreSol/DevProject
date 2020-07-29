package com.HagekopStudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.HagekopStudios.entities.Bullet;
import com.HagekopStudios.entities.Enemy;
import com.HagekopStudios.entities.Entity;
import com.HagekopStudios.entities.Player;
import com.HagekopStudios.graficos.Spritesheet;
import com.HagekopStudios.graficos.UI;
import com.HagekopStudios.world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	/**
	 * 
	 */

	private static final long serialVersionUID = 0l;

	private boolean isRunning = true;

	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 3;

	public static int CUR_LEVEL = 1;

	public static BufferedImage image;

	private Thread thread;
	private UI ui;

	public static List<Entity> entities;
	public static List<Bullet> Bullets;
	public static List<Enemy> enemies;

	public static JFrame frame;

	public static World world;

	public static Player player;

	public Menu menu;
	public static String GameState = "MENU";
	public static String map;
	public static String MapTipe = "DEFAULT";
	
	public static Spritesheet spritesheet;
	public static Spritesheet uiSprite;
	public static Spritesheet uiDamege;

	public static Random rand;

	public void StanceValues() {
		
		newmap();

		rand = new Random();
		ui = new UI();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		spritesheet = new Spritesheet("/spritesheet.png");
		uiSprite = new Spritesheet("/UI.png");
		uiDamege = new Spritesheet("/UIDamege.png");
		player = new Player(0, 0, 16, 16, spritesheet.getSprite(33, 1, 16, 16));
		Bullets = new ArrayList<Bullet>();
		entities.add(player);
		world = new World("/" + map);
		
		menu = new Menu();
	}

	public Game() {
		
		StanceValues();
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		initFrame();
		
	}

	public void initFrame() {

		frame = new JFrame("Stalin and a Communist's Journey");
		
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

	public synchronized void start() {

		thread = new Thread(this);
		isRunning = true;
		thread.start();

	}

	public synchronized void stop() {

		isRunning = false;

		try {

			thread.join();

		} catch (InterruptedException e) {

			e.printStackTrace();

		}
	}

	public void newmap() {

		map = "mapa" + CUR_LEVEL + ".png";
	}

	public void resetGame() {

		Game.image = new BufferedImage(Game.WIDTH, Game.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Game.entities = new ArrayList<Entity>();
		Game.enemies = new ArrayList<Enemy>();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.uiSprite = new Spritesheet("/UI.png");
		Game.uiDamege = new Spritesheet("/UIDamege.png");
		Game.player = new Player(0, 0, 16, 16, Game.spritesheet.getSprite(33, 1, 16, 16));

		Game.entities.add(Game.player);
		Game.world = new World("/" + Game.map);
		Game.player.life = 100;
	
	}

	public void tick() {
		if (GameState.equals("NORMAL")) {
			for (int i = 0; i < entities.size(); i++) {

				Entity e = entities.get(i);

				newmap();

				e.tick();
			}
			for (int i = 0; i < Bullets.size(); i++) {
				Bullets.get(i).tick();
			}
		} else if (GameState.equals("GAME_OVER")) {
			
		}else if(GameState.equals("MENU")) {
			
		}
	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {

			this.createBufferStrategy(3);
			return;

		}

		Graphics g = image.getGraphics();

		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		world.render(g);
		for (int i = 0; i < Bullets.size(); i++) {
			Bullets.get(i).render(g);
		}
		for (int i = 0; i < entities.size(); i++) {

			Entity e = entities.get(i);

			e.render(g);

		}
		ui.render(g);
		g.setFont(new Font("Arial", Font.BOLD, 9));
		if (player.getAmmo() == 0) {
			g.setColor(new Color(255, 0, 0));
		} else {
			g.setColor(new Color(0, 255, 0));
		}
		g.drawString("" + player.getAmmo(), 45, 153);
		g.drawString("" + player.getReservaMuni(), 30, 153);
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		if (Game.GameState.equals("GAME_OVER")) {
			
			Graphics2D g2d = (Graphics2D) g;

			g2d.setColor(new Color(0, 0, 0, 100));
			g2d.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("GAME OVER", ((WIDTH * SCALE)) / 2-50, ((HEIGHT * SCALE)) / 2-20);
			g.drawString("> pressione enter para continuar <", ((WIDTH * SCALE)) / 2-200, ((HEIGHT * SCALE)) / 2+40);
		}else if(Game.GameState.equals("MENU")) {
			menu.render(g);
		}
		
		bs.show();

	}

	@Override
	public void run() {

		long lastTime = System.nanoTime();

		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();

		int frames = 0;

		requestFocus();
		while (isRunning) {

			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {

				tick(); 
				render();

				frames++;
				delta--;

			}

			if (System.currentTimeMillis() - timer >= 1000) {

				System.out.println("FPS: " + frames);

				frames = 0;
				timer += 1000;

			}
		}

		stop();

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.setRight(true);

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.setLeft(true);

		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.setUp(true);

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.setDown(true);

		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.shoot = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.setRight(false);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.setLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.setUp(false);
			menu.up = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.setDown(false);
			if(GameState == "MENU") {
				menu.down = true;
			}
		}
		if(this.GameState.equals("GAME_OVER") && e.getKeyCode() == KeyEvent.VK_ENTER) {
				this.GameState = "NORMAL";
				this.resetGame();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		player.mouseShoot = true;
		player.setMx((e.getX() / SCALE));
		player.setMy((e.getY() / SCALE));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
