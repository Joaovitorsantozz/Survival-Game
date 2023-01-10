package nnrg.gameobjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import nnrg.gameobject.entitys.Player;
import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;
import nnrg.main.Game;
import nnrg.main.LoadImage;
import nnrg.world.Depth;

public class Spear extends GameObject implements Tickable, Renderable {
	private GameObject owner;
	private BufferedImage img;
	private double angle = 0, angle2;
	private boolean animation;

	public Spear(int x, int y, ID id, GameObject owner) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.owner = owner;
		img = new LoadImage("/spair.png").getImage();
		setDepth(Depth.HIGHT);
		setWidth(16);
		setHeight(48);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(Math.toRadians(angle), getX() + velX, getY() + getHeight() / 2 + 5 + velY);

		g.drawImage(img, getX(), owner.getY(), getWidth(), getHeight(), null);

		g2.rotate(-Math.toRadians(angle), getX() + velX, getY() + getHeight() / 2 + 5 + velY);
	}

	@Override
	public void Update() {
		doall();
	}

	public void doall() {

		if (((Player) owner).input.attack.down) {
			if (((Player) owner).stamina > 0)
				animation = true;
		}
		if (animation) {
			if (owner.getDir() == 1 || owner.getDir() == 0) {
				if (angle < 140) {
					angle += 15;
					velX += 0.5f;
					velY += 0.5f;

				} else {
					angle = 0;
					animation = false;
					velX = 0;
					velY = 0;
				}
			} else {
				if (angle > -140) {
					angle -= 15;
					velX += 0.5f;
					velY += 0.5f;

				} else {
					angle = 0;
					animation = false;
					velX = 0;
					velY = 0;
				}
			}
		}

		if (owner.getDir() != 1)
			setX(owner.getX() - getWidth() / 2);
		else
			setX(owner.getX() + owner.getWidth() - getWidth() + 5);

		setY(owner.getY());
	}

	public void followOwner() {
		if (owner.getDir() != 1) {
			x += ((owner.getX() - x - 10)) * 1f;

		} else {
			x += ((owner.getX() - x + owner.getWidth() + getWidth())) * 0.1f;

		}
		y += ((owner.getY() - y)) * 0.1f;
	}

	public void render2(Graphics g) {

	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
