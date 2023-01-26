package nnrg.main.menu;

import java.awt.Graphics;

import nnrg.main.Game;
import nnrg.main.InputHandler;

public class Menu {
	private Game game;
	protected InputHandler input;

	public void init(Game game, InputHandler input) {
		this.game=game;
		this.input=input;
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		
	}
	
	public Game getGame() {return this.game;}
}
