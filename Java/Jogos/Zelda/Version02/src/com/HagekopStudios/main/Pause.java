package com.HagekopStudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pause {
	
	public static String[] options = {"Reset","Loading","Save","Continuar","Exit"};
	public static int currentOption = 0;
	public static int maxOption = options.length -1;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean saveExists = false;
	public static boolean saveGame = false;
	public static void tick() {
		if(up) {
			currentOption--;
			up = false;
			if(currentOption < 0 ) {
				currentOption = maxOption;
			}
		}else if(down) {
			currentOption++;
			down = false;
			if(currentOption < 0) {
				currentOption = maxOption;
			}
			if(currentOption >= 5) {
				currentOption = 0;
			}
		}
		
	}
	public static void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(new Color(0, 0, 0, 100));
		g2d.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		
		g.setColor(new Color(79,0,0));
		g.fillRect(0, 0, 200, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(222, 220, 89));
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Stalin and a", 40, 25);
		g.drawString("Communist's", 35, 45);
		g.drawString("Journey", 55, 65);
		g.setColor(Color.WHITE);
		g.drawString("Restart", 40, 130);
		g.drawString("Loading", 40, 160);
		g.drawString("Save", 40, 190);
		g.drawString("Return", 40, 220);
		g.drawString("Exit", 40, 250);
		
		if(options[currentOption] == "Reset") {
		
			g.drawString(">", 20, 130);
		
		}else if(options[currentOption] == "Loading") {
		
			g.drawString(">", 20, 160);
		
		}
		
		else if(options[currentOption] == "Save") {
		
			g.drawString(">", 20, 190);
		
		}else if(options[currentOption] == "Continuar") {
		
			g.drawString(">", 20, 220);
		
		}
		
		else if(options[currentOption] == "Exit") {
		
			g.drawString(">", 20, 250);
		
		}
	}
}
