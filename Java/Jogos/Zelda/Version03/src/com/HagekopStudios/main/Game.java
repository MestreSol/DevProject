package com.HagekopStudios.main;

import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.HagekopStudios.gfx.Screen;
import com.HagekopStudios.gfx.SpriteSheet;

public class Game extends Canvas implements Runnable, GameConfig {
	
	/**
	 * 
	 */
	public static String GameState = "STARTING";
	public static final String Name = "null";
	public static int CUR_LEVEL = 1;
	public static boolean running = false;
	private static final long serialVersionUID = 3L;
	private BufferedImage image = new BufferedImage(GameConfig.WIDTH,GameConfig.HEIGTH, BufferedImage.TYPE_INT_RGB);
	private int[] colors = new int[256];
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private Screen screen;
	private Screen lightScreen;
	public void start() {
		running = true;
		new Thread(this).start();
	}
	public void stop() {
		running = false;
	}
	private void init() {
		int pp = 0;
		for(int r = 0; r<6;r++) {
			for(int g = 0; g<6;g++) {
				for(int b = 0; b < 6 ; b++){
					int rr = (r*255/5);
					int gg = (g*255/5);
					int bb = (b*255/5);
					
					int mid = (rr*30+gg*59+bb*11)/100;
					
					int r1 = ((rr+mid*1)/2)*230/255+10;
					int g1 = ((gg+mid*1)/2)*230/255+10;
					int b1 = ((bb+mid*1)/2)*230/255+10;
					
					colors[pp++] = r1<<16|g1<<8|b1;
				}
			}
		}
		try {
			screen = new Screen(GameConfig.WIDTH,GameConfig.HEIGTH, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("sprite.png"))));
			lightScreen = new Screen(GameConfig.WIDTH, GameConfig.HEIGTH, new SpriteSheet(ImageIO.read(Game.class.getResourceAsStream("sprite.png"))));
		}catch(IOException e) {
			e.printStackTrace();
		}
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}
