package nnrg.gameobjects;

import java.awt.Rectangle;
import java.util.Random;

import nnrg.gameobject.entitys.Enemy;
import nnrg.interfaces.Tickable;
import nnrg.main.Game;
import nnrg.main.others.FontStyle;
import nnrg.main.others.Text;
import nnrg.world.Depth;

public class Spawner extends GameObject implements Tickable {
	
	public static int enemys,wave;
	public int spawntime = 60 * 5, tickCount;
	private boolean canSpawn = false, spawned = false, cancount;
	private int enemyAtRound = 4;

	public Spawner(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		setDepth(Depth.HIGHT);
		Game.text = new Text(FontStyle.getFont(100, 1), "Wave " + wave, Game.W / 2, Game.H / 2);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle(getX() - 240, getY() - 240, 256 * 2, 256 * 2);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		// tickcount
		if (cancount) {
			tickCount++;
		}
		if (tickCount == spawntime) {
			canSpawn = true;
			cancount = false;
			tickCount = 0;
		}
		if (enemys == 0) {
			cancount = true;
			if (spawned) {
				spawned = false;
				wave++;
				Game.handlergame.setMenu(Game.handlergame.screen);
				enemyAtRound+=5;
			}
		}
		if (canSpawn) {

			spawn();
		}
		if (Game.handlergame.getMenu() != null) {
			tickCount = 0;
			cancount = false;
		}
	}

	private void spawn() {

		for (int i = 0; i < enemyAtRound; i++) {
			int xp = (new Random().nextInt(10) + new Random().nextInt(6)) * 32;
			int yp = (new Random().nextInt(8) + new Random().nextInt(8)) * 32;
			if (enemys < enemyAtRound) {
				if (getBounds().getX() + xp < getBounds().getX() + getBounds().getWidth()) {
					Game.handler.object
							.add(new Enemy((int) getBounds().getX() + xp, (int) getBounds().getY() + yp, ID.Enemy));
					enemys++;
				} else {
					xp = (new Random().nextInt(5) * 32);
					Game.handler.object
							.add(new Enemy((int) getBounds().getX() + xp, (int) getBounds().getY() + yp, ID.Enemy));
					enemys++;
				}
			}
			canSpawn = false;
		}
		spawned = true;
	}

	public static int getEnemyAtRound() {
		return enemys;
	}
	public static int getWave() {
		return wave;
	}
}
