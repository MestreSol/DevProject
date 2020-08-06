package com.HagekopStudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {

	public BufferedImage fundo;

	public String[] options = { "New Game", "Loading", "Random Game", "Exit" };

	public int currentOption = 0;
	public int maxOption = options.length - 1;

	public boolean up = false;
	public boolean down = false;

	public static boolean saveExists = false;
	public static boolean saveGame = false;

	public Menu() {

		try {

			fundo = ImageIO.read(getClass().getResource("/Fundo.jpg"));

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public void tick() {

		File file = new File("save.Stalin");

		if (file.exists()) {

			saveExists = true;

		} else {

			saveExists = false;

		}

		if (up) {

			currentOption--;
			up = false;

			if (currentOption < 0) {

				currentOption = maxOption;

			}

		} else if (down) {

			currentOption++;
			down = false;

			if (currentOption < 0) {

				currentOption = maxOption;

			}

			if (currentOption >= 3) {

				currentOption = 0;

			}
		}
	}

	public static void saveGame(String[] val1, int[] val2, int code) {

		BufferedWriter write = null;

		try {

			write = new BufferedWriter(new FileWriter("save.stalin"));

		} catch (IOException e) {

			e.printStackTrace();

		}

		for (int i = 0; i < val1.length; i++) {

			String current = val1[i];
			current += ":";
			char[] value = Integer.toString(val2[i]).toCharArray();

			for (int in = 0; in < value.length; in++) {

				value[in] += code;
				current += value[in];

			}

			try {

				write.write(current);

				if (i < val1.length - 1) {

					write.newLine();

				}

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		try {

			write.flush();
			write.close();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	public static String loadGame(int code) {

		String line = "";
		File file = new File("save.stalin");

		if (file.exists()) {

			String singleLine = null;

			try {

				BufferedReader reader = new BufferedReader(new FileReader("save.stalin"));

				try {

					while ((singleLine = reader.readLine()) != null) {

						String[] trans = singleLine.split(":");
						char[] val = trans[1].toCharArray();
						trans[1] = "";

						for (int i = 0; i < val.length; i++) {

							val[i] -= code;
							trans[1] += val[i];

						}

						line += trans[0];
						line += ":";
						line += trans[1];
						line += "/";

					}

				} catch (IOException e) {

					e.printStackTrace();

				}

			} catch (FileNotFoundException e) {

				e.printStackTrace();

			}
		} else {

			return line;

		}
		return line;
	}

	public static void applySave(String str) {

		String[] spl = str.split("/");

		for (int i = 0; i < spl.length; i++) {

			String[] spl2 = spl[i].split(":");

			switch (spl2[0]) {

			case "level":

				Game.CUR_LEVEL = Integer.parseInt(spl2[1]);
				System.out.println("Lever: " + Game.CUR_LEVEL);

				Game.GameState = "NORMAL";
				Game.resetGame();
				Game.DefinirMusica();

				break;
			case "bullet":

				Game.player.setAmmo(Integer.parseInt(spl2[2]));
				System.out.println("Bullet: " + Game.player.getAmmo());
				break;
			case "reserva":

				Game.player.setReservaMuni(Integer.parseInt(spl2[3]));
				System.out.println("Reserva Muni: " + Game.player.getReservaMuni());

				break;
			}
		}

	}

	public void render(Graphics g) {
		g.drawImage(fundo, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
		g.setColor(new Color(79, 0, 0));
		g.fillRect(0, 0, 200, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(222, 220, 89));
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Stalin and a", 40, 25);
		g.drawString("Communist's", 35, 45);
		g.drawString("Journey", 55, 65);
		g.setColor(Color.WHITE);
		g.drawString("New Game", 40, 130);
		g.drawString("Loading", 40, 160);
		g.drawString("Random Game", 40, 190);
		g.drawString("Exit", 40, 220);

		if (options[currentOption] == "New Game") {
			g.drawString(">", 20, 130);
		} else if (options[currentOption] == "Loading") {
			g.drawString(">", 20, 160);
		} else if (options[currentOption] == "Random Game") {
			g.drawString(">", 20, 190);
		} else if (options[currentOption] == "Exit") {
			g.drawString(">", 20, 220);
		}

	}

}
