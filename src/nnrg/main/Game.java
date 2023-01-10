package nnrg.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import nnrg.gameobject.entitys.Player;
import nnrg.gameobjects.GameObjectHandler;
import nnrg.gameobjects.ID;
import nnrg.gameobjects.Spawner;

public class Game extends Canvas implements Runnable {
	public static GameObjectHandler handler;
	public static final int W = 1230, H = 730;
	public static HandlerGame handlergame;
	private boolean isRunning = false;
	private double Frames, upd;
	private FontStyle style;
	private Clock clock;
	public static Text text;
	private static String gamestate = "play";
	public static InputHandler inpt;
	public FontX font = new FontX();
	private Windows wind;
	public Game() {
		inpt = new InputHandler(this);
		wind=new Windows(W, H, "LRG", this);
		new FontStyle();
		text = new Text(FontStyle.getFont(100, 1), "Wave ", Game.W / 2, Game.H / 2);
		new Player(500, 500, ID.Player, handler, inpt);
		handler = new GameObjectHandler();
		handlergame = new HandlerGame();

		style = new FontStyle();
		clock = new Clock();

		start();
	}

	public static void main(String[] args) {
		new Game();
	}

	private void UpdateCam() {
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				handlergame.cam.tick(handler.object.get(i));
			}
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		while (isRunning) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			if (shouldRender) {
				wind.clear();
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				Frames = frames;
				System.out.println(Frames);
				frames = 0;
				ticks = 0;
			}
		}
	}

	private void start() {
		isRunning = true;
		new Thread(this).start();
	}

	public void tick() {

		requestFocus();
		if (!hasFocus()) {
			inpt.releaseAll();
		} else {
			handler.update();
			UpdateCam();
			handlergame.tick();
			clock.Update();
			text.tick();
			inpt.tick();
		}

	}

	public void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;

		// Show images here v

		g2.translate(-handlergame.cam.getX(), -handlergame.cam.getY());
		handler.render(g2);
		g2.translate(handlergame.cam.getX(), handlergame.cam.getY());

		g.setColor(Color.white);

		g.setFont(FontStyle.getFont(40, Font.BOLD));

		clock.render(g);
		text.render(g2);

		handlergame.renderNotAffect(g2);
		g.drawImage(wind.getImage(),0,0,W,H,null);
		// Show images here ^
		g.dispose();
		bs.show();

	}
	
	
}