package com.HagekopStudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	public BufferedImage fundo;
	public String[] options = {"New Game","Loading","Exit"};
	public int currentOption = 0;
	public int maxOption = options.length -1;
	public boolean up = false;
	public boolean down = false;
	public Menu() {
		try {
			fundo = ImageIO.read(getClass().getResource("/fundo.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void tick() {
		if(up) {
			currentOption--;
			up = false;
			if(currentOption < 0 ) {
				currentOption = maxOption;
			}
		}else if(down) {
			currentOption++;
			down = false;
			if(currentOption > 0) {
				currentOption = maxOption;
			}
		}
	}
	public void render(Graphics g) {
		g.drawImage(fundo, 0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE,null);
		g.setColor(new Color(79,0,0));
		g.fillRect(0, 0, 200, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(222, 220, 89));
		g.setFont(new Font("Arial",Font.BOLD,20));
		g.drawString("Stalin and a", 40, 25);
		g.drawString("Communist's", 35, 45);
		g.drawString("Journey", 55, 65);
		g.setColor(Color.WHITE);
		g.drawString("New Game", 40, 130);
		g.drawString("Loading", 40, 160);
		g.drawString("Exit", 40, 190);
		
		if(options[currentOption] == "New Game") {
		g.drawString(">", 20, 130);
		}else if(options[currentOption] == "Loading") {
			g.drawString(">", 20, 160);
			}
		else if(options[currentOption] == "Exit") {
			g.drawString(">", 20, 190);
			}
		
	}

}
