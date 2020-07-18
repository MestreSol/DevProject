import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {
	private BufferedImage spritesheet;
	public Spritesheet(String path) {
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BufferedImage getImagem(int x,int y, int whidth, int height) {
		return spritesheet.getSubimage(x, y, whidth, height);
	}
}