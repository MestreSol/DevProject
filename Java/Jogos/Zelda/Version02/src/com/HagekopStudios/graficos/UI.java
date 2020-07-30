package com.HagekopStudios.graficos;

import java.awt.Color;
import java.awt.Graphics;

import com.HagekopStudios.main.Game;

public class UI {

	public UI() {
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(2, 2, 7, 100);
		if(Game.player.sobrevida >= 1) {
			
			g.setColor(Color.green);
			g.fillRect(2, 2, 7, (int) ((Game.player.sobrevida / Game.player.maxLife) * 100));
		}
		g.setColor(Color.red);
		g.fillRect(2, 67, 7, 100);
		g.setColor(Color.green);
		g.fillRect(2, 67, 7, (int) ((Game.player.life / Game.player.maxLife) * 100));
		
		if (Game.player.isDamege) {

			g.drawImage(Game.uiDamege.getSprite(0, 0, 240, 160), 0, 0, Game.WIDTH, Game.HEIGHT, null);

			Game.player.isDamege = false;
		} else {
			g.drawImage(Game.uiSprite.getSprite(0, 0, 240, 160), 0, 0, Game.WIDTH, Game.HEIGHT, null);
		}
	}
}
