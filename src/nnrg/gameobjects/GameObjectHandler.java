package nnrg.gameobjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import nnrg.gameobject.entitys.Player;
import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;
import nnrg.main.Game;
import nnrg.main.HandlerGame;
import nnrg.world.Tile;

public class GameObjectHandler {
	public List<GameObject> object = new ArrayList<>();

	private boolean right = false, left = false, up = false, down = false, attack = false;
	public Player player;
	Tile[] tile;

	public void update() {
		for (int i = 0; i < object.size(); i++) {
			GameObject ee = object.get(i);
			if (ee instanceof Tickable) {
				((Tickable) ee).Update();
			}

		}

		object.sort(GameObject.nodeSorter);

	}

	public void render(Graphics2D g) {
		int xstart = (int) Game.handlergame.cam.getX() >> 4; 
		int ystart = (int) Game.handlergame.cam.getY() >> 4;

		int xfinal = xstart + Game.W >> 4;
		int yfinal = ystart + Game.H >> 4;
	
		for (int i = 0, n = object.size(); i < n; i++) {
			GameObject ee = object.get(i);
			if (ee instanceof Renderable) {

				((Renderable) ee).render(g);

			}
		}
	
	}

	public void renderNotAffect(Graphics g) {

	}

	public void ClearObjects() {

		object.clear();

	}

	public void add(GameObject obj) {
		object.add(obj);
	}

	public void DeleteObject(GameObject obj) {
		object.remove(obj);
	}

	public void DeleteByTag(ID id) {
		for (int i = 0, n = object.size(); i < n; i++) {
			if (object.get(i).getId() == id)
				object.remove(object.get(i));
		}
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
}
