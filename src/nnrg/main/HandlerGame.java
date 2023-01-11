package nnrg.main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import nnrg.gameobject.entitys.Player;
import nnrg.gameobjects.Camera;
import nnrg.main.menu.Menu;
import nnrg.main.menu.Upgradescreen;
import nnrg.main.others.FontStyle;
import nnrg.main.others.SpriteSheet;
import nnrg.world.Level;

public class HandlerGame {
	public static FontStyle font;
	public static SpriteSheet spr, grassspr;
	public Camera cam;
	public static Level level;
	public static Player player;

	public Menu menu;
	public Upgradescreen screen;
	public HandlerGame(Game game) {
		spr = new SpriteSheet("/spritesheet.png");
		level = new Level("/map.png", 0,game);
		cam = level.getCamera();
		player = level.getPlayer();
		screen = new Upgradescreen();
		
	}

	public void tick() {
		setMenu(screen);
	}
	public void setMenu(Menu mn) {
		this.menu=mn;
	}
	public void render(Graphics g) {
	}

	public void renderNotAffect(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(menu!=null)menu.render(g);
	}
	
	public Menu getMenu() {
		return this.menu;
	}
}
