package nnrg.world;

import java.awt.Color;
import java.util.Random;

import nnrg.gameobject.entitys.Player;
import nnrg.gameobjects.Camera;
import nnrg.gameobjects.ID;
import nnrg.gameobjects.Spawner;
import nnrg.gameobjects.Spear;
import nnrg.gameobjects.levelobj.Pilar;
import nnrg.gameobjects.levelobj.Tree;
import nnrg.main.Game;
import nnrg.main.LoadImage;

public class Level extends World {
	public int offset;
	public int sort;
	public int[] p;
	private Player player;
	private Camera camera;

	public Level(String dir, int Offset, Game game) {
		super(dir);
		offset = Offset;
		setDir(dir);
		spr = new LoadImage(getDir()).getImage();
		setWidth(spr.getWidth());
		setHeight(spr.getHeight());
		for (int xx = 0; xx < getWidth(); xx++) {
			for (int yy = 0; yy < getHeight(); yy++) {
				p = new int[getWidth() * getHeight()];
				int pa = spr.getRGB(xx, yy);
				spr.getRGB(0, 0, getWidth(), getHeight(), p, 0, getWidth());
				BitMap(xx, yy, pa);

				if (pa == 0xFF0026FF) {
					Game.handler.add(new Tile(xx * 32, yy * 32, ID.Floor, TileType.Floor));
					player = new Player(xx * 32, yy * 32, ID.Player, Game.handler, Game.inpt);
					Game.handler.add(player);
					camera = new Camera(xx * 32, yy * 32, player);
					Game.handler.add(new Spear(xx * 32, yy * 32, ID.Weapon, player));
				}
				if (pa == 0xFF000000) {
					Game.handler.add(new Tile(xx * 32, yy * 32, ID.Floor, TileType.Floor));
				}
				if (pa == 0xFFFFFFFF) {
					if (getPixel(xx + 1, yy) < getWidth()) {
						if (getPixel(xx + 1, yy) == 0xFF000000) {
							Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.LeftFloor));
							Game.handler.add(new Tile(xx * 32 + 32, yy * 32, ID.Floor, TileType.RightSdw));

						}
						if (getPixel(xx - 1, yy) == 0xFF000000) {
							Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.RightFloor));

							Game.handler.add(new Tile(xx * 32 - 32, yy * 32, ID.Floor, TileType.LeftSdw));

						}

					} 

					if (getPixel(xx, yy + 1) < getHeight()) {
						if (getPixel(xx, yy + 1) == 0xFF000000) {
							Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.BottomFloor));
							Game.handler.add(new Tile(xx * 32, yy * 32 + 64, ID.Block, TileType.WallMed));
							Game.handler.add(new Tile(xx * 32, yy * 32 + 32, ID.Block, TileType.WallDown));
						} else if (getPixel(xx, yy - 1) == 0xFF000000) {
							Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.TopFloor));
						}
					}
				}
				if (pa == 0xFF00FF21 || pa == 0xFFFFFF00) {
					Game.handler.add(new Tile(xx * 32, yy * 32, ID.Block, TileType.Wall));
					int pos = new Random().nextInt(100);
					if (pos > 95) {
						Game.handler.add(new Tree(xx * 32 - 96, yy * 32 - 96, ID.Tree));
					} else if (pos > 90 && pos < 96) {
						// Game.handler.add(new Bush(xx * 32 - 24, yy * 32 - 24, ID.Tree));
					}
				}

				if (pa == 0xFFFF0000) {
					Game.handler.add(new Spawner(xx * 32, yy * 32, ID.Spawner));
					Game.handler.add(new Tile(xx * 32, yy * 32, ID.Floor, TileType.StoneFloor));
				}

				if (pa == 0xFF404040) {
					Game.handler.add(new Tile(xx * 32, yy * 32, ID.Floor, TileType.StoneRoad));
				}
				if (pa == 0xFF808080) {
					Game.handler.add(new Tile(xx * 32, yy * 32, ID.Floor, TileType.StoneFloor));
				}
				if (pa == 0xFFB200FF) {
					Game.handler.add(new Pilar(xx * 32 - 24, yy * 32 - 48, ID.Block));
					Game.handler.add(new Tile(xx * 32, yy * 32, ID.Floor, TileType.Floor));
				}
			}
		}

	}

	public int getPixel(int x, int y) {
		int[] p;
		int pa = 0;

		p = new int[getWidth() * getHeight()];
		pa = spr.getRGB(x, y);
		spr.getRGB(0, 0, getWidth(), getHeight(), p, 0, getWidth());

		return pa;
	}

	public Player getPlayer() {
		return player;
	}

	public Camera getCamera() {
		return camera;
	}
}
