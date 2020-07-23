package com.HagekopStudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.HagekopStudios.main.Game;
import com.HagekopStudios.world.Camera;

public class Entity {
	
	protected int x;
	protected int y;
	
	private int width;
	private int height;
	private int maskx = 16;
	private int masky = 16;
	private int mwidth;
	private int mheight;
	private BufferedImage sprite;

	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(97, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(97, 16, 16, 16);
	public static BufferedImage WEAPONTWO_EN = Game.spritesheet.getSprite(97, 32, 16, 16);
	public static BufferedImage MUNI_EN = Game.spritesheet.getSprite(112, 64, 16, 16);
	public static BufferedImage ENEMI_EN = Game.spritesheet.getSprite(112, 0, 16, 16);
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public void tick() {}
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.sprite = sprite;
		
		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	
	}
	public void setMask(int maskx, int masky,int mwidth,int mheight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}
	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1Mack = new Rectangle(e1.getX()+ e1.maskx,e1.getY()+e1.masky, e1.mwidth, e1.mheight);
		Rectangle e2Mack = new Rectangle(e2.getX()+ e2.maskx,e2.getY()+e2.masky, e2.mwidth, e2.mheight);
		return e1Mack.intersects(e2Mack);
	}
	public void render(Graphics g) {
	
		g.drawImage(this.getSprite(),this.getX() - Camera.x	,this.getY() - Camera.y ,null);
	
	}
	
}
