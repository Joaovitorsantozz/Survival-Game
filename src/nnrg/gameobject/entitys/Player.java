package nnrg.gameobject.entitys;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import nnrg.gameobjects.GameObject;
import nnrg.gameobjects.GameObjectHandler;
import nnrg.gameobjects.ID;
import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;
import nnrg.main.Game;
import nnrg.main.InputHandler;
import nnrg.main.LoadImage;
import nnrg.main.others.Animator;
import nnrg.world.Depth;

public class Player extends GameObject implements Tickable, Renderable {
	public GameObjectHandler hand;

	private BufferedImage spr = new LoadImage("/player2.png").getImage(), anim[], idle[],
			attack = new LoadImage("/attacck.png").getImage(), attacking[];
	Animator an, atck;
	private float speed = 3f;
	private boolean drawattack;

	public InputHandler input;
	public boolean rechargeStam, refillstam;
	public int stamina = 80, maxStam = 80, count;
	public int damage;
	public BufferedImage stamin = new LoadImage("/upg/raio.png").getImage(),
			emptystam = new LoadImage("/upg/emptyraio.png").getImage();

	public Player(int x, int y, ID id, GameObjectHandler hand, InputHandler input) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		this.hand = hand;
		setDepth(Depth.HIGHT + 5);
		setWidth(16 * 3);
		setHeight(16 * 3);
		an = new Animator(20, 8);
		atck = new Animator(5, 8);
		anim = new LoadImage("/player2.png").CutHor(8, 0, 0, 16, 16, spr);
		idle = new LoadImage("/player2.png").CutHor(8, 0, 16, 16, 16, spr);
		attacking = new LoadImage("/attacck.png").CutHor(9, 0, 0, 8, 8, attack);
		atck.setAnimation(attacking);
		atck.setMaxFrames(10);
		this.input = input;
		
		
	}

	@Override
	public void Update() {
		super.tick();
		// create a move method
		setVelX(0);
		setVelY(0);
		if (input.up.down)
			velY = -speed;
		if (input.down.down)
			velY = speed;
		if (input.left.down)
			velX = -speed;
		if (input.right.down)
			velX = speed;
		x += velX;
		y += velY;

		if (input.attack.down) {
			if (stamina > 0) {
				stamina -= 10;
				input.attack.down = false;
				rechargeStam = false;
				Attack();
				drawattack = true;

			}

		}
		if (!input.attack.down) {
			count++;
			if (count > 120) {
				count = 0;
				rechargeStam = true;
			}
		}

		refillStam();
		Col();
		setDir();
	}

	private void refillStam() {
		if (rechargeStam) {
			if (stamina < 80)
				stamina++;
			else
				rechargeStam = false;
		}
	}

	private void setDir() {
		if (Move()) {
			if (velX < 0)
				dir = -1;
			if (velX > 0)
				dir = 1;

		}
	}

	protected void Col() {
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject e = Game.handler.object.get(i);
			if (e.getId() == ID.Block) {
				if (e.getBounds().intersects(this.getBounds())) {
					x += velX * -1;
					y += velY * -1;
				}
			}
		}
	}

	public void Attack() {
		for (int i = 0; i < hand.object.size(); i++) {
			GameObject ee = hand.object.get(i);
			if (ee.getId() == ID.Enemy) {
				if (ee instanceof Enemy) {
					damage(5, ee, getAttackBound());
				}
			}
		}

	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (Move()) {
			an.setAnimation(anim);
			an.setMaxFrames(15);
		} else {
			an.setAnimation(idle);
			an.setMaxFrames(40);
		}
		if (getDir() != 1) {
			g.drawImage(an.getAnimation(), getX() + 44, getY(), getWidth() * -1, getHeight(), null);
		} else if (getDir() != -1) {
			drawDefaultTex(g, an.getAnimation());
		}
		
		drawAttack(g);
		renderpUI(g);
		
		
	}

	public void renderpUI(Graphics g) {
		for (int i = 0; i < maxStam / 10; i++) {

			g.drawImage(emptystam, (x - 20) + i * 10, (getY() - 20), 10, 15, null);
		}
		for (int i = 0; i < stamina / 10; i++) {

			g.drawImage(stamin, (x - 20) + i * 10, (getY() - 20), 10, 15, null);
		}

	}

	private void drawAttack(Graphics g) {
		if (drawattack) {

			if (getDir() != 1) {
				g.drawImage(atck.getAnimation(), getX() - 24, getY() - 24, getWidth() * 2 * -1, 96, null);
			} else if (getDir() == 1) {
				g.drawImage(atck.getAnimation(), getX() + 48, getY() - 24, getWidth() * 2, 96, null);
			}
		}
		checkAttackAnim();
	}

	void checkAttackAnim() {
		if (atck.getIndex() == atck.getMaxIndex() - 1) {
			drawattack = false;
			atck.setIndex(0);
		}
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX(), getY() + getHeight() - getHeight() / 3, getWidth(), getHeight() / 3);
	}

	public Rectangle getAttackBound() {
		// TODO Auto-generated method stub
		if (dir != 1) {
			return new Rectangle(getX() - (getWidth() - 15) * 2, getY() - getHeight(), (getWidth() + 10) * 2,
					getHeight() * 3);
		} else if (dir == 1) {
			return new Rectangle(getX(), getY() - getHeight(), (getWidth() + 10) * 2, getHeight() * 3);
		}
		return null;
	}
	
	
	public void init(BufferedImage img) {
	
		for(int xx=0;xx<img.getWidth();xx++) {
			for (int yy=0;yy<img.getHeight();yy++) {
				
			}
		}
		
	}
	
	
	
	
	
	
	
}
