package com.HagekopStudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.HagekopStudios.entities.Enemy;
import com.HagekopStudios.entities.Entity;
import com.HagekopStudios.entities.Lifepack;
import com.HagekopStudios.entities.Muni;
import com.HagekopStudios.entities.Weapon;
import com.HagekopStudios.main.Game;

public class World {

	public static Tile[] tiles;
	
	public static int WIDTH, HEIGHT;
	
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
	
		try {
		
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			int[] pixels = new int[map.getWidth()*map.getHeight()];
			
			tiles = new Tile[map.getWidth()*map.getHeight()];
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0,map.getWidth());
			
			for(int xx = 0; xx< map.getWidth(); xx++) {
				
				for(int yy = 0; yy < map.getHeight(); yy++) {
			
					int pixelA = pixels[xx+(yy*map.getWidth())]; 
					
					if(Game.MapTipe.equals("PRISION")) {
						tiles[xx+(yy*WIDTH) ] = new FloorTile(Tile.TILE_FLOOR_PRISION,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(Game.MapTipe.equals("DEFAULT")) {
						tiles[xx+(yy*WIDTH) ] = new FloorTile(Tile.TILE_FLOOR,xx*TILE_SIZE,yy*TILE_SIZE);
					}
					if(pixelA == 0xFFFF00DC) {
					
						Game.player.setX(xx*TILE_SIZE);
						Game.player.setY(yy*TILE_SIZE);
					
					}else if(pixelA == 0xFF7F3300) {
					
						Game.entities.add(new Weapon(xx*TILE_SIZE,yy*TILE_SIZE,TILE_SIZE,TILE_SIZE,Entity.WEAPONTWO_EN));
					
					}else if(pixelA == 0xFFFF0000) {
					
						Enemy en = new Enemy(xx*TILE_SIZE,yy*TILE_SIZE,TILE_SIZE,TILE_SIZE,Entity.ENEMI_EN);
						Game.entities.add(en);
						Game.enemies.add(en);
					
					}else if(pixelA == 0xFF0094FF) {
					
						Game.entities.add(new Muni(xx*TILE_SIZE,yy*TILE_SIZE,TILE_SIZE,TILE_SIZE,Entity.MUNI_EN));
					
					}else if(pixelA == 0xFFB6FF00) {
					
						Game.entities.add(new Lifepack(xx*TILE_SIZE,yy*TILE_SIZE,TILE_SIZE,TILE_SIZE,Entity.LIFEPACK_EN));
					
					}else if(pixelA == 0xFFFFFFFF) {
					
						tiles[xx+(yy*WIDTH) ] = new WallTile(Tile.TILE_WALL,xx*TILE_SIZE,yy*TILE_SIZE);
					
					}else if(pixelA == 0xFF000000) { 
					
					}else if(pixelA == 0xFF8CFFAA) {
						tiles[xx+(yy*WIDTH)] = new WallTile(Tile.TILE_WALL_PRISION_CORNER_RIGTH_TOP,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF67BC7F) {
						tiles[xx+(yy*WIDTH)] = new WallTile(Tile.TILE_WALL_PRISION_TOP,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF00FF43) {
						tiles[xx+(yy*WIDTH)] = new WallTile(Tile.TILE_WALL_PRISION_RIGTH,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF267F42) {
						tiles[xx+(yy*WIDTH)] = new WallTile(Tile.TILE_WALL_PRISION_RIGTH_NONE,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF002108) {
						tiles[xx+(yy*WIDTH)] = new WallTile(Tile.TILE_WALL_PRISION_END_RIGTH,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF529665) {
						tiles[xx+(yy*WIDTH)] = new WallTile(Tile.TILE_WALL_PRISION_MID_TOP,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF00AD34) {
						tiles[xx+(yy*WIDTH)] = new WallTile(Tile.TILE_WALL_PRISION_END_TOP,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF808080) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_FLOOR_PRISION,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF2A4933) {
						tiles[xx+(yy*WIDTH)] = new WallTile(Tile.TILE_WALL_PRISION_LEFT,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFFE0FFE8) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_WALL_PRISION_CORNER_LEFT_TOP,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF3AFF5E) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_WALL_PRISION_CORNER_MID_LEFT,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF398F50) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_WALL_PRISION_CORNER_END_LEFT,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF396846) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_WALL_PRISION_CORNER_RIGTH_DOWN,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF4E8E5F) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_WALL_PRISION_CORNER_LEFT_DOWN,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF19542A) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_WALL_PRISION_END_LEFT,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF265132) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_WALL_PRISION_CORNER_RIGTHTOP,xx*TILE_SIZE,yy*TILE_SIZE);
					}else if(pixelA == 0xFF305139) {
						tiles[xx+(yy*WIDTH)] = new FloorTile(Tile.TILE_WALL_PRISION_RIGTH,xx*TILE_SIZE,yy*TILE_SIZE);
					}
				} 
			}
		} catch (IOException e) {
			
			e.printStackTrace();

		}
	}
	public static boolean isFree(int xnext, int ynext) {
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1)/TILE_SIZE;
		int y2 = ynext/TILE_SIZE;
		
		int x3 = xnext/TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1)/TILE_SIZE; 
		int y4 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		
		return !((tiles[x1+(y1*World.WIDTH)] instanceof WallTile || 
				tiles[x2+(y2*World.WIDTH)] instanceof WallTile ||
				tiles[x3+(y3*World.WIDTH)] instanceof WallTile || 
				tiles[x4+(y4*World.WIDTH)] instanceof WallTile));
	
	}
	
	public void render(Graphics g) {
	
		int xstart = Camera.x/TILE_SIZE;
		int ystart = Camera.y/TILE_SIZE;
		
		int xfinal = xstart+Game.WIDTH/TILE_SIZE+1;
		int yfinal = ystart+Game.HEIGHT/TILE_SIZE+1;
		
		for(int xx = xstart; xx<xfinal;xx++) {
		
			for(int yy = ystart;yy <yfinal;yy++) {
			
				if(xx < 0 || yy<0 || xx>=WIDTH || yy>=HEIGHT) continue;
				
				Tile tile = tiles[xx+(yy*WIDTH)];
				tile.render(g);
			
			}
		}
	}
}
