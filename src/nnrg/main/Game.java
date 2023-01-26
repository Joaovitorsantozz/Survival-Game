
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
import nnrg.main.others.Clock;
import nnrg.main.others.FontStyle;
import nnrg.main.others.FontX;
import nnrg.main.others.Text;

public class Game extends Canvas implements Runnable {
	public static GameObjectHandler handler;
	public static final int W = 1230, H = 730;
	public static HandlerGame handlergame;
	private boolean isRunning = false;

	private FontStyle style;
	private Clock clock;
	public static Text text;
	private static String gamestate = "play";
	public static InputHandler inpt;
	public FontX font = new FontX();
	private Windows wind;
	private int fps;
	public Renderer render;
	private Image image;

	public Game() {
		image = new Image("/teste.png");
		wind = new Windows(this);
		render = new Renderer(this);
		inpt = new InputHandler(this);

		// wind=new Windows(W, H, "LRG", this);

		style = new FontStyle();
		text = new Text(FontStyle.getFont(100, 1), "Wave ", Game.W / 2, Game.H / 2);

		handler = new GameObjectHandler();
		handlergame = new HandlerGame(this, inpt);

		clock = new Clock();

		start();
	}

	public static void main(String[] args) {
		new Game();
	}

	private void UpdateCam() {

		handlergame.cam.tick();

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
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				fps=frames;
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
			if (handlergame.getMenu() == null) {
				handler.update();
				UpdateCam();
				clock.Update();
				text.tick();
				inpt.tick();
			} else {
				handlergame.getMenu().tick();
			}
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
		g.setColor(new Color(119, 116, 59));
		g.fillRect(0, 0, W, H);
		// Show images here v
		g2.translate(-handlergame.cam.getX(), -handlergame.cam.getY());
		handler.render(g2);
		g2.translate(handlergame.cam.getX(), handlergame.cam.getY());

		g.setColor(Color.white);

		clock.render(g);
		// text.render(g2);

		handlergame.renderNotAffect(g2);
		// font.draw("FPS "+(int)(fps+10), 50,50, g2,40,24);
		font.draw("FPS " + fps, 50, 50, g2, 40, 26);

		// Show images here ^
		g.dispose();
		bs.show();

	}

	public void draw() {
		render.drawImage(image, 300, 300);
	}

	public Windows getWindows() {
		return this.wind;
	}
}
