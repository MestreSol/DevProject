package com.HagekopStudios.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import com.HagekopStudios.main.Game;
import com.HagekopStudios.world.Camera;
import com.HagekopStudios.world.Node;
import com.HagekopStudios.world.Vector2i;
import com.HagekopStudios.world.World;

public class Entity {
	
	protected int x;
	protected int y;
	
	private int width;
	private int height;
	protected int maskx = 16;
	protected int masky = 16;
	protected int maskw = 10;
	protected int maskh = 10;
	protected int mwidth;
	protected int mheight;
	
	private BufferedImage sprite;

	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(97, 0, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(97, 16, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage WEAPONTWO_EN = Game.spritesheet.getSprite(97, 32, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage MUNI_EN = Game.spritesheet.getSprite(112, 64, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage ENEMI_EN = Game.spritesheet.getSprite(112, 0, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage GUN_LEFT = Game.spritesheet.getSprite(176, 0, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage GUN_UP = Game.spritesheet.getSprite(160, 16, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage GUN_RIGHT = Game.spritesheet.getSprite(160, 0, World.TILE_SIZE, World.TILE_SIZE);
	public static BufferedImage GUN_DONW = Game.spritesheet.getSprite(160, 16, World.TILE_SIZE, World.TILE_SIZE);
	
	protected  boolean right = false;
	protected  boolean up = false;
	protected  boolean left = false;
	protected  boolean down = false;
	protected  boolean moved = false;
	
	protected List<Node> path;
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
	public void followPath(List<Node> path) {
		
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size()-1).tile;
				//xprev = x;
				//yprev = y;
				if(x < target.x*World.TILE_SIZE) {
					left = true;
					moved = true;
					x += Enemy.speed;
				}else if(x > target.x*World.TILE_SIZE) {
					moved = true;
					right = true;
					x -= Enemy.speed;
				}
				if(y < target.y*World.TILE_SIZE) {
					moved = true;
					up = true;
					y += Enemy.speed;
				}else if(y > target.y*World.TILE_SIZE) {
					
					moved = true;
					down = true;
					y -= Enemy.speed;
				}else {
					moved = false;
					down = true;
				}
			
				if(x == target.x*World.TILE_SIZE && y == target.y *World.TILE_SIZE) {
					path.remove(path.size()-1);
				}
				
				
			}
		}
	}
	public double Distancia(int x1,int y1, int x2,int y2) {
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	public boolean isColiddingWithPlayer() {

		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx, this.getY() + masky, maskw, maskh);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);

		return enemyCurrent.intersects(player);

	}
	public static boolean isColidding(Entity e1, Entity e2) {
	
		Rectangle e1Mack = new Rectangle(e1.getX()+ e1.maskx,e1.getY()+e1.masky, e1.mwidth, e1.mheight);
		Rectangle e2Mack = new Rectangle(e2.getX()+ e2.maskx,e2.getY()+e2.masky, e2.mwidth, e2.mheight);
		
		return e1Mack.intersects(e2Mack);
	
	}
	public void render(Graphics g) {
	
		g.drawImage(this.getSprite(),this.getX() - Camera.x	,this.getY() - Camera.y ,null);
	
	}
	
	public boolean isColidding(int xnext, int ynext) {

		Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh);

		for (int i = 0; i < Game.enemies.size(); i++) {

			Enemy e = Game.enemies.get(i);

			if (e == this)
				continue;

			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw, maskh);

			if (enemyCurrent.intersects(targetEnemy)) {

				return true;

			}

		}

		return false;
	}
}
