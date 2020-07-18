//Modelo base
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{


	private boolean isRunning = false;
	private final int WIDTH = 160;
	private final int HEIGHT = 120;
	private final int SCALE = 4;
	
	private Thread th;
	private BufferedImage imagem;
	public static JFrame frame;
	private Spritesheet sheet;
	private BufferedImage player;
	
	public Game() {
	
		sheet = new Spritesheet("TesteSprite.png");
		player = sheet.getImagem(0,0,16,16);
		
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		
		imagem = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	}
	
	public void initFrame() {
	
		frame = new JFrame("game #1");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	}
	
	public static void main(String[] args) {
	
		Game game = new Game();
		game.start();
	}
	public synchronized void start() {
	
		th = new Thread(this);
		isRunning = true;
		th.start();
	
	}
	public synchronized void stop() {
		
		isRunning = false;
		
		try {
		
			th.join();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void tick() {
		
	}
	public void render() {
	
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
		
			this.createBufferStrategy(3);
			return;
		
		}
		
		Graphics g = imagem.getGraphics();
		
		g.setColor(new Color(19,19,19));
		g.fillRect(0, 0,WIDTH, HEIGHT);
		
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.setColor(Color.WHITE);
		
		g.drawString("FPS: ", 90, 90);
		
		//RenderGame
		
		g.drawImage(player,20,20,null);
		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		
		g.drawImage(imagem,0,0, WIDTH*SCALE,HEIGHT*SCALE,null);
		
		bs.show();
	
	}
	public void run() {
		
		
		long lastTime = System.nanoTime();
		double FPS = 60;
		double ns = 1000000000 / FPS;
		double delta = 0;
		long now = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();

		while(isRunning) {
			now = System.nanoTime();
			delta +=(now-lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				tick();
				render();
				frames++;
				delta--;
			}if(System.currentTimeMillis()-timer >=1000) {
				System.out.println("FPS: "+frames);
				frames = 0;
				timer +=1000;
			}
		}
		stop();
	}

}