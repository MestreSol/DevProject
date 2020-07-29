package com.HagekopStudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HagekopStudios.main.Game;

public class Tile {
	
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_FLOOR_PRISION = Game.spritesheet.getSprite(0, 32, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);
	public static BufferedImage TILE_WALL_PRISION_CORNER_RIGTH_TOP = Game.spritesheet.getSprite(32, 192, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_TOP = Game.spritesheet.getSprite(48, 192, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_MID_TOP = Game.spritesheet.getSprite(48, 208, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_END_TOP = Game.spritesheet.getSprite(48, 224, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_RIGTH = Game.spritesheet.getSprite(16, 208, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_END_RIGTH = Game.spritesheet.getSprite(16, 224, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_RIGTH_NONE = Game.spritesheet.getSprite(32,208,World.TILE_SIZE,World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_LEFT = Game.spritesheet.getSprite(64, 208, World.TILE_SIZE,World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_CORNER_LEFT_TOP = Game.spritesheet.getSprite(64, 192, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_CORNER_MID_LEFT = Game.spritesheet.getSprite(80, 208, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_CORNER_END_LEFT = Game.spritesheet.getSprite(80, 224, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_CORNER_RIGTH_DOWN = Game.spritesheet.getSprite(16, 192, World.TILE_SIZE,World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_CORNER_LEFT_DOWN = Game.spritesheet.getSprite(80, 192, World.TILE_SIZE,World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_END_LEFT = Game.spritesheet.getSprite(0, 208, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage TILE_WALL_PRISION_CORNER_RIGTHTOP = Game.spritesheet.getSprite(0, 192, World.TILE_SIZE, World.TILE_SIZE);
	private BufferedImage sprite;
	
	private int x,y;
	 
	public void render(Graphics g) {
		g.drawImage(sprite,x - Camera.x, y - Camera.y, null);
	}

	public Tile(BufferedImage sprite, int x, int y) {
		
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	} 
	
	
			
	
}
