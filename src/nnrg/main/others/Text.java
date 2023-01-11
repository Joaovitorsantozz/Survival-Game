package nnrg.main.others;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import nnrg.main.Game;

public class Text {
	private Font font;
	private int x, y;
	private String text;
	public boolean decrease, increase = true, cld;
	private int alpha;
	private int cooldown;

	public Text(Font font, String text, int x, int y) {
		if (font == null) {
			this.font = new Font("Arial", Font.BOLD, 20);
		}
		this.font = font;
		this.x = x;
		this.y = y;
		this.text = text;
	}	
	public Text(String text) {
		this.text=text;
	}
	public void tick() {

		if (decrease && alpha >= 1) {
			increase = false;
			alpha -= 2;
		}
		if (increase && alpha < 254) {
			decrease = false;
			alpha += 2;
			cld = true;
		}

		if (cld) {
			cooldown++;
			if (cooldown == 60 * 3) {
				cld = false;
				cooldown = 0;
				decrease = true;
			}
		}

	}

	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, y - 95, Game.W, 130);
		g.setColor(new Color(255, 255, 255, alpha));
		g.setFont(font);
		g.drawString(text, x - text.length() * 30, y);

	}

	public void renderWhitefont(int size, int x, int y, Graphics g) {
		g.setFont(FontStyle.getFont(size, 1));
		g.setColor(Color.white);
		g.drawString(text, x, y);
	}

	public void renderPixefont(int size, int x, int y, Graphics g) {
		g.setFont(FontStyle.getFont2(size, 1));
		g.setColor(Color.white);
		g.drawString(text, x, y);
	}
}
