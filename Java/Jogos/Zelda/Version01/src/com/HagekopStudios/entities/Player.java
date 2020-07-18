package com.HagekopStudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HagekopStudios.main.Game;
import com.HagekopStudios.world.Camera;
import com.HagekopStudios.world.World;

public class Player extends Entity{

	private boolean right,up,left,down;
	public int speed = 1;
	private int frames = 0, maxFrame = 7, index = 0, maxIndex = 5;
	private boolean moved = false;
	private BufferedImage[] RightPlayer;
	private BufferedImage[] LeftPlayer;
	private BufferedImage[] TopPlayer;
	private BufferedImage[] DownPlayer;
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		RightPlayer = new BufferedImage[5];
		LeftPlayer = new BufferedImage[5];
		TopPlayer = new BufferedImage[5];
		DownPlayer = new BufferedImage[5];
		
		for(int i = 0; i<5;i++) {
			RightPlayer[i] = Game.spritesheet.getSprite(64, 0+(16*i), 16, 16);
		}
		for(int i = 0; i<5;i++) {
			LeftPlayer[i] = Game.spritesheet.getSprite(80, 0+(16*i), 16, 16);
		}
		
		for(int i = 0; i<5;i++) {
			TopPlayer[i] = Game.spritesheet.getSprite(48, 0+(16*i), 16, 16);
		}
		
		for(int i = 0; i<5;i++) {
			DownPlayer[i] = Game.spritesheet.getSprite(32, 0+(16*i), 16, 16);
		}
		
		
	}
	public void tick() {
		moved = false;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
		this.setX(this.getX()+speed);
		}else if(left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			this.setX(this.getX()-speed);
			
		}
		if(up && World.isFree(this.getX(),(int)(y+speed))) {
			moved = true;
			this.setY(this.getY()-speed);
		}else if(down && World.isFree(this.getX(),(int)(y-speed))) {
			moved = true;
			this.setY(this.getY()+speed);
		}
		if(moved) {
			frames ++;
			if(frames == maxFrame) {
				frames = 0;
				index++;
				if(index>=maxIndex) {
					index =2;
				}
			}
			
		}else {
			frames++;
			if(frames == maxFrame) {
				frames = 0;
				if(index > 1) {
					index = 0;
					return;
				}
				if(index == 0) {
					index++;
				}else {
					index --;
				}
			}
			}
		Camera.x =  Camera.clamp(this.x - (Game.WIDTH/2),0,World.WIDTH *16- Game.WIDTH);
		Camera.y =  Camera.clamp(this.y - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	public void render(Graphics g) {
		if(right) {
			g.drawImage(RightPlayer[index],this.getX() - Camera.x,this.getY()- Camera.y,null);
		}
		else if(left) {
			g.drawImage(LeftPlayer[index],this.getX() - Camera.x,this.getY()- Camera.y,null);
		}else if(up) {
			g.drawImage(TopPlayer[index],this.getX() - Camera.x,this.getY()- Camera.y,null);
		}else if(down) {
			g.drawImage(DownPlayer[index],this.getX() - Camera.x,this.getY()- Camera.y,null);	
		}else {
			g.drawImage(DownPlayer[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
		
	}
}