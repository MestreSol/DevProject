package com.HagekopStudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HagekopStudios.main.Game;
import com.HagekopStudios.world.Camera;

public class Entity {
	protected int x;
	protected int y;
	private int width;
	private int height;
	
	private BufferedImage sprite;

	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(97, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(97, 16, 16, 16);
	public static BufferedImage WEAPONTWO_EN = Game.spritesheet.getSprite(97, 32, 16, 16);
	public static BufferedImage MUNI_EN = Game.spritesheet.getSprite(113, 32, 16, 16);
	public static BufferedImage ENEMI_EN = Game.spritesheet.getSprite(113, 0, 16, 16);
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
	}
	public void render(Graphics g) {
		g.drawImage(this.getSprite(),this.getX() - Camera.x	,this.getY() - Camera.y ,null);
	}
	
}