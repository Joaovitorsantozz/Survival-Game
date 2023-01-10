package nnrg.gameobject.entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import nnrg.gameobjects.GameObject;
import nnrg.gameobjects.ID;
import nnrg.gameobjects.Spawner;
import nnrg.gameobjects.particles.Damageparticle;
import nnrg.interfaces.Living;
import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;
import nnrg.main.Animator;
import nnrg.main.Game;
import nnrg.main.LoadImage;
import nnrg.main.SpriteSheet;
import nnrg.main.UI.Lifebar;
import nnrg.world.Depth;

public class Enemy extends GameObject implements Renderable, Tickable, Living {
	private BufferedImage enemy = new LoadImage("/enemy.png").getImage(), idle[], deading[], feedbacks,
			dead = new LoadImage("/enemytreedead.png").getImage();
	private Animator an, an2;
	private Lifebar bar;
	private int cooldown;
	private boolean canDead, Dead, isDeading;

	public Enemy(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.life = 20;
		setWidth(22 * 3);
		setHeight(24 * 3);
		setDepth(Depth.MEDIUM);
		an = new Animator(30, 5);
		an2 = new Animator(10, 8);
		idle = new LoadImage("/enemy.png").CutHor(5, 0, 0, 22, 24, enemy);
		bar = new Lifebar(this, life, 20);
		feedbacks = new SpriteSheet("/enemy.png").getSprite(110, 0, 22, 24);
		deading = new LoadImage("/enemytreedead.png").CutHor(8, 0, 0, 22, 24, dead);

	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub

	//	move();

		cooldown();
		animdeath();

	}

	private void animdeath() {
		if (this.life <= 0) {
			
			isDeading = true;
			an2.setAnimation(deading);
		}

		if (isDeading) {
			if (an2.getIndex() == an2.getMaxIndex() - 2) {
				Dead = true;
			}
		}

		if (Dead == true) {
			Spawner.enemys--;
			Game.handler.DeleteObject(this);
		}
	}

	private void cooldown() {
		if (attacked) {
			cooldown++;
			if (cooldown > 10) {
				cooldown = 0;
				attacked = false;
			}
		}

	}

	private void move() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject ee = Game.handler.object.get(i);
			if (ee.getId() == ID.Player) {

				if (!isColliding(getX() - 2, getY())) {
					if (getX() > ee.getX())
						x -= 1;
				}
				if (!isColliding(getX() + 2, getY())) {
					if (getX() < ee.getX())
						x += 1;
				}
				if (!isColliding(getX(), getY() + 2)) {
					if (getY() < ee.getY())
						y += 1;
				}
				if (!isColliding(getX(), getY() - 2)) {
					if (getY() > ee.getY())
						y -= 1;
				}
			}
		}
	}

	private boolean colliding() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject ee = Game.handler.object.get(i);
			if (ee.getId() == ID.Enemy) {
				if (ee != this) {
					if (getBounds().intersects(((Enemy) ee).getBounds())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isColliding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext, ynext, (int) getBounds().getWidth(),
				(int) getBounds().getHeight());
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject e = Game.handler.object.get(i);
			if (e == this)
				continue;
			if (e.getId() == ID.Enemy) {

				Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), e.getWidth(), e.getHeight());
				if (enemyCurrent.intersects(targetEnemy)) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.red);

		an.setAnimation(idle);
		an.setMaxFrames(30);

		if (!isDeading) {
			if (!attacked)
				drawDefaultTex(g, an.getAnimation());
			else
				g.drawImage(feedbacks, getX(), getY(), getWidth(), getHeight(), null);
		} else {
			drawDefaultTex(g, an2.getAnimation());

		}

		if (!isDeading)
			bar.render(g);
		
		
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX() + getWidth() / 2 - 5, getY() + 20, 10, getHeight() - 20);
	}

	public Rectangle getRealBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

}
