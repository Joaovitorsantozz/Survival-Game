package nnrg.gameobjects;

import java.awt.Rectangle;
import java.util.Random;

import nnrg.gameobject.entitys.Enemy;
import nnrg.interfaces.Tickable;
import nnrg.main.FontStyle;
import nnrg.main.Game;
import nnrg.main.Text;
import nnrg.world.Depth;

public class Spawner extends GameObject implements Tickable {
	public int wave = 1;
	public static int enemys;
	public int spawntime = 60 * 10, tickCount;
	private boolean canSpawn = true, spawned = false;
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
		//tickcount
		if (tickCount == spawntime) {
			canSpawn = true;
			tickCount = 0;
		}
		if (enemys == 0) {
			if (spawned) {
				spawned = false;
				wave++;
				Game.text = new Text(FontStyle.getFont(100, 1), "Wave " + wave, Game.W / 2, Game.H / 2);

			}
		}
		if (canSpawn) {

			spawn();
		}

	}

	private void spawn() {

		for (int i = 0; i < enemyAtRound; i++) {
			int xp = (new Random().nextInt(10) + new Random().nextInt(6)) * 32;
			int yp = (new Random().nextInt(8) + new Random().nextInt(8)) * 32;
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
			canSpawn = false;
		}
		spawned = true;
	}
	public static int getEnemyAtRound() {
		return enemys;
	}
}
