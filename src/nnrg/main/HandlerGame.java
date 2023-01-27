package nnrg.main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import nnrg.gameobjects.Camera;
import nnrg.main.menu.DeadScreen;
import nnrg.main.menu.Menu;
import nnrg.main.menu.Stats;
import nnrg.main.menu.Upgradescreen;
import nnrg.main.others.FontStyle;
import nnrg.main.others.SpriteSheet;
import nnrg.world.Level;

public class HandlerGame {
	public static FontStyle font;
	public static SpriteSheet spr, grassspr;

	public static Level level;

	public Camera cam;
	public Menu menu;
	public Upgradescreen screen;
	public Stats stat;
	public HandlerGame(Game game,InputHandler input) {
		
		spr = new SpriteSheet("/spritesheet.png");
		level = new Level("/map.png", 0,game);
		screen = new Upgradescreen(input);
		cam=level.getCamera();
		stat=new Stats(level.getPlayer());
	}

	public void tick() {
		
	}
	public void setMenu(Menu mn) {
		this.menu=mn;
	}
	public void render(Graphics g) {
	}

	public void renderNotAffect(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(menu!=null)
			menu.render(g);
		else
			stat.render(g2);
	}
	
	public Menu getMenu() {
		return this.menu;
	}
}
