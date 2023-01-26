package nnrg.world;

import java.awt.image.BufferedImage;
import java.util.Random;

import nnrg.main.HandlerGame;

public enum TileType {
	Floor(), TopFloor(), LeftFloor(), RightFloor(), BottomFloor(), Wall(), WallDown(),WallMed(),RightSdw(),LeftSdw(),StoneFloor(),StoneRoad();

	public BufferedImage setImage(BufferedImage spr, Tile t) {
		switch (this) {
		case Floor:
			int type = new Random().nextInt(100);
			if (type <= 90) {
				spr = HandlerGame.spr.getSprite(0, 0, 16, 16);
			} else if (type > 28) {
				int type2 = new Random().nextInt(10);
				if (type2 <= 7)
					spr = HandlerGame.spr.getSprite(16, 0, 16, 16);
				else if (type2 > 7)
					spr = HandlerGame.spr.getSprite(32, 0, 16, 16);
			}
			break;
		case Wall:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(0, 16, 16, 16);
			break;
		case WallDown:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(0, 32, 16, 16);
			break;
		case TopFloor:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(48, 16, 16, 16);
			break;
		case BottomFloor:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(16, 16, 16, 16);
			break;
		case RightFloor:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(32, 16, 16, 16);
			break;
		case LeftFloor:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(64, 16, 16, 16);
			break;
		case WallMed:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(16, 32, 16, 16);
			break;
		case RightSdw:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(0,48, 16, 16);
			break;
		case LeftSdw:
			t.setDepth(Depth.MEDIUM-2);
			spr = HandlerGame.spr.getSprite(16,48, 16, 16);
			break;
		case StoneFloor:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(16,64,16,16);
			break;
		case StoneRoad:
			t.setDepth(Depth.MEDIUM);
			spr = HandlerGame.spr.getSprite(0,64,16,16);
			break;
		default:
			break;
		}
		return spr;
	}
}
