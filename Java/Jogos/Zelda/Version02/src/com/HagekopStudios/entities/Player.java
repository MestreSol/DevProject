package com.HagekopStudios.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.HagekopStudios.main.Game;
import com.HagekopStudios.main.Sound;
import com.HagekopStudios.world.Camera;
import com.HagekopStudios.world.World;

public class Player extends Entity {

	public double life = 100, maxLife = 100;
	public double sobrevida = 0;
	
	public int speed = 2;
	private int frames = 0, maxFrame = 7, index = 0, maxIndex = 5,AtualFrame = 0;
	private int ammo = 12;
	private int maxAmmo = 12;
	private int reservaMuni = 0;
	private int DamegeFrames = 0;

	private BufferedImage[] RightPlayer;
	private BufferedImage[] LeftPlayer;
	private BufferedImage[] TopPlayer;
	private BufferedImage[] DownPlayer;
	private BufferedImage[] RightPlayerDamege;
	private BufferedImage[] LeftPlayerDamege;
	private BufferedImage[] TopPlayerDamege;
	private BufferedImage[] DownPlayerDamege;
	public static double angle = 0,dx=0,dy=0;
	public boolean shoot = false;
	public boolean mouseShoot = false;
	public boolean isDamege = false;
	private boolean right, up, left, down;
	private boolean moved = false;
	private boolean arma = false;
	private int mx, my;

	public boolean isMouseShoot() {
		return mouseShoot;
	}

	public void setMouseShoot(boolean mouseShoot) {
		this.mouseShoot = mouseShoot;
	}

	public int getMx() {
		return mx;
	}

	public void setMx(int mx) {
		this.mx = mx;
	}

	public int getMy() {
		return my;
	}

	public void setMy(int my) {
		this.my = my;
	}

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

		for (int i = 0; i < 5; i++) {

			RightPlayer[i] = Game.spritesheet.getSprite(64, 0 + (16 * i), 16, 16);

		}

		for (int i = 0; i < 5; i++) {

			LeftPlayer[i] = Game.spritesheet.getSprite(80, 0 + (16 * i), 16, 16);

		}

		for (int i = 0; i < 5; i++) {

			TopPlayer[i] = Game.spritesheet.getSprite(48, 0 + (16 * i), 16, 16);

		}

		for (int i = 0; i < 5; i++) {

			DownPlayer[i] = Game.spritesheet.getSprite(32, 0 + (16 * i), 16, 16);

		}
		RightPlayerDamege = new BufferedImage[5];
		LeftPlayerDamege = new BufferedImage[5];
		TopPlayerDamege = new BufferedImage[5];
		DownPlayerDamege = new BufferedImage[5];

		for (int i = 0; i < 5; i++) {

			RightPlayerDamege[i] = Game.spritesheet.getSprite(64, 96 + (World.TILE_SIZE * i), World.TILE_SIZE,
					World.TILE_SIZE);

		}

		for (int i = 0; i < 5; i++) {

			LeftPlayerDamege[i] = Game.spritesheet.getSprite(80, 96 + (World.TILE_SIZE * i), World.TILE_SIZE,
					World.TILE_SIZE);

		}

		for (int i = 0; i < 5; i++) {

			TopPlayerDamege[i] = Game.spritesheet.getSprite(48, 96 + (World.TILE_SIZE * i), World.TILE_SIZE,
					World.TILE_SIZE);

		}

		for (int i = 0; i < 5; i++) {

			DownPlayerDamege[i] = Game.spritesheet.getSprite(32, 96 + (World.TILE_SIZE * i), World.TILE_SIZE,
					World.TILE_SIZE);

		}

	}

	public void reload() {
		if (this.reservaMuni <= 0) {
			return;
		} else {
			this.ammo = this.reservaMuni;
			if (ammo > maxAmmo) {
				reservaMuni = ammo - maxAmmo;
				ammo = 12;
			} else {
				reservaMuni = 0;
			}
		}
	}

	public void tick() {
		if (shoot && arma) {
			Sound.shoot.play();
			
			
			if (!(this.ammo <= 0)) {
				int dx;
			
				int px = 0;
				int py = 0;
				shoot = false;
				if (right) {
					dx = 1;
					px = 10;
					py = 7;
				} else {
					py = 7;
					px = -10;
					dx = -1;
				}
				Bullet bullet = new Bullet(this.getX() + px, this.getY() + py, 2, 2, null, dx, 0);
				Game.Bullets.add(bullet);
			
				ammo--;
			} else {
				reload();
			}
			}
		if(frames >= 5 ) {
		if (mouseShoot && arma) { 
			
			if (!(this.ammo <= 0)) {
				angle = Math.atan2(my - (this.getY() + (World.TILE_SIZE / 2) - Camera.y), mx - (this.getX() + (World.TILE_SIZE / 2) - Camera.x));
				 dx = Math.cos(angle);
				 dy = Math.sin(angle);
				
				int px = 8;
				int py = 8;
				mouseShoot = false;
				if (right) {
					px = 10;
					py = 7;
				} else {
					py = 7;
					px = -10;

				}
				Bullet bullet = new Bullet(this.getX() + px, this.getY() + py, 2, 2, null, dx, dy);
				Game.Bullets.add(bullet);
				Sound.shoot.play();
				ammo--;
			} else {
				reload();
			}}
		}
		moved = false;

		if (right && World.isFree((int) (x + speed), this.getY())) {

			moved = true;
			this.setX(this.getX() + speed);

		} else if (left && World.isFree((int) (x - speed), this.getY())) {

			moved = true;
			this.setX(this.getX() - speed);

		}
		if (up && World.isFree(this.getX(), (int) (y - speed))) {

			moved = true;
			this.setY(this.getY() - speed);

		} else if (down && World.isFree(this.getX(), (int) (y + speed))) {

			moved = true;
			this.setY(this.getY() + speed);

		}

		if (this.life <= 0) {
			Game.GameState = "GAME_OVER";
			return;
		}

		if (moved) {

			frames++;

			if (frames == maxFrame) {

				frames = 0;
				index++;

				if (index >= maxIndex) {

					index = 2;

				}
			}

		} else {

			frames++;

			if (frames == maxFrame) {

				frames = 0;

				if (index > 1) {

					index = 0;

					return;
				}

				if (index == 0) {

					index++;

				} else {

					index--;

				}
			}
		}

		checkLifePack();
		checkAmmo();
		checkArma();
		if (this.isDamege) {
			Sound.DamegePlayer.play();
			this.DamegeFrames++;
			if (this.DamegeFrames == 30) {
				this.DamegeFrames = 0;
				this.isDamege = false;
			}
		}
		Camera.x = Camera.clamp(this.x - (Game.WIDTH / 2), 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.y - (Game.HEIGHT / 2), 0, World.HEIGHT * 16 - Game.HEIGHT);

	}

	public int getMaxAmmo() {
		return maxAmmo;
	}

	public void setMaxAmmo(int maxAmmo) {
		this.maxAmmo = maxAmmo;
	}

	public int getReservaMuni() {
		return reservaMuni;
	}

	public void setReservaMuni(int reservaMuni) {
		this.reservaMuni = reservaMuni;
	}

	public int getDamegeFrames() {
		return DamegeFrames;
	}

	public void setDamegeFrames(int damegeFrames) {
		DamegeFrames = damegeFrames;
	}

	public BufferedImage[] getRightPlayerDamege() {
		return RightPlayerDamege;
	}

	public void setRightPlayerDamege(BufferedImage[] rightPlayerDamege) {
		RightPlayerDamege = rightPlayerDamege;
	}

	public BufferedImage[] getLeftPlayerDamege() {
		return LeftPlayerDamege;
	}

	public void setLeftPlayerDamege(BufferedImage[] leftPlayerDamege) {
		LeftPlayerDamege = leftPlayerDamege;
	}

	public BufferedImage[] getTopPlayerDamege() {
		return TopPlayerDamege;
	}

	public void setTopPlayerDamege(BufferedImage[] topPlayerDamege) {
		TopPlayerDamege = topPlayerDamege;
	}

	public BufferedImage[] getDownPlayerDamege() {
		return DownPlayerDamege;
	}

	public void setDownPlayerDamege(BufferedImage[] downPlayerDamege) {
		DownPlayerDamege = downPlayerDamege;
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public boolean isDamege() {
		return isDamege;
	}

	public void setDamege(boolean isDamege) {
		this.isDamege = isDamege;
	}

	public boolean isArma() {
		return arma;
	}

	public void setArma(boolean arma) {
		this.arma = arma;
	}

	public void checkLifePack() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if (e instanceof Lifepack) {
				if (Entity.isColidding(this, e)) {

					
					life += Game.rand.nextInt(100);
					
					if(life > maxLife) {
						this.sobrevida += life-100;
						life = 100;
						if(this.sobrevida >=101) {
							this.sobrevida = 100;
						}
					}
					Game.entities.remove(i);
					return;
				}
			}
		}
	}

	public void checkArma() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if (e instanceof Weapon) {
				if (Entity.isColidding(this, e)) {

					arma = true;

					Game.entities.remove(i);
					return;
				}
			}
		}
	}

	public void checkAmmo() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if (e instanceof Muni) {
				if (Entity.isColidding(this, e)) {

					ammo += Game.rand.nextInt(6);
					if (ammo >= maxAmmo) {
						if (this.reservaMuni >= 64) {
							return;
						} else {
							reservaMuni += ammo - maxAmmo;
							ammo = maxAmmo;
						}
					}
					Game.entities.remove(i);
					return;
				}
			}
		}
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public double getLife() {
		return life;
	}

	public void setLife(double life) {
		this.life = life;
	}

	public double getMaxLife() {
		return maxLife;
	}

	public void setMaxLife(double maxLife) {
		this.maxLife = maxLife;
	}

	public int getFrames() {
		return frames;
	}

	public void setFrames(int frames) {
		this.frames = frames;
	}

	public int getMaxFrame() {
		return maxFrame;
	}

	public void setMaxFrame(int maxFrame) {
		this.maxFrame = maxFrame;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(int maxIndex) {
		this.maxIndex = maxIndex;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public BufferedImage[] getRightPlayer() {
		return RightPlayer;
	}

	public void setRightPlayer(BufferedImage[] rightPlayer) {
		RightPlayer = rightPlayer;
	}

	public BufferedImage[] getLeftPlayer() {
		return LeftPlayer;
	}

	public void setLeftPlayer(BufferedImage[] leftPlayer) {
		LeftPlayer = leftPlayer;
	}

	public BufferedImage[] getTopPlayer() {
		return TopPlayer;
	}

	public void setTopPlayer(BufferedImage[] topPlayer) {
		TopPlayer = topPlayer;
	}

	public BufferedImage[] getDownPlayer() {
		return DownPlayer;
	}

	public void setDownPlayer(BufferedImage[] downPlayer) {
		DownPlayer = downPlayer;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (!isDamege) {

			if (right) {

				g.drawImage(RightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if (arma) {
				//	g2d.rotate(angulo);
					g2d.drawImage(Entity.GUN_RIGHT, this.getX() + 8 - Camera.x, this.getY() + 2 - Camera.y, null);
				}
			}

			else if (left) {

				g.drawImage(LeftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if (arma) {
					g.drawImage(Entity.GUN_LEFT, this.getX() - 8 - Camera.x, this.getY() + 2 - Camera.y, null);
				}

			} else if (up) {

				g.drawImage(TopPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			} else if (down) {
				if (arma) {
				}
				g.drawImage(DownPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			} else {
				if (arma) {
				}
				g.drawImage(DownPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			}

		} else {
			this.isDamege = false;
			if (right) {
				if (arma) {
				}
				g.drawImage(RightPlayerDamege[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			}

			else if (left) {
				if (arma) {
				}
				g.drawImage(LeftPlayerDamege[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			} else if (up) {

				g.drawImage(TopPlayerDamege[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			} else if (down) {
				if (arma) {
				}
				g.drawImage(DownPlayerDamege[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			} else {

				g.drawImage(DownPlayerDamege[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			}
		}
	}
}
