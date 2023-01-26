package nnrg.main.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import nnrg.gameobjects.Spawner;
import nnrg.main.Game;
import nnrg.main.InputHandler;
import nnrg.main.LoadImage;
import nnrg.main.others.FontStyle;
import nnrg.main.others.FontX;
import nnrg.main.others.Text;

public class Upgradescreen extends Menu {
	private BufferedImage opt = new LoadImage("/upg/upgscreen.png").getImage(),
			triangle = new LoadImage("/upg/selected.png").getImage();
	private FontX font;

	private InputHandler input;
	public int selected = 0;
	private float rotation, y;
	private boolean incR = true, decR;
	public Upgrade[] upgrade = {
			new Upgrade(new LoadImage("/upg/sword.png").getImage(), "Improve your streength stats by 1",1),
			new Upgrade(new LoadImage("/upg/agility.png").getImage(), "Improve your agility stats by 05",2),
			new Upgrade(new LoadImage("/upg/stamina.png").getImage(), "Improve your stamina stats by 5",3), };

	public Upgradescreen(InputHandler input) {
		font = new FontX();
		this.input = input;

	}

	public void tick() {
		super.tick();
		if (input.down.down) {
			input.down.down = false;
			selected++;

			if (selected > 2) {
				selected = 0;
			}
		}
		if (input.up.down) {
			input.up.down = false;
			selected--;
			if (selected < 0)
				selected = 2;
		}
		if (input.menu.down) {
			upgrade[selected].setValue();
			Game.handlergame.setMenu(null);
			Game.text = new Text(FontStyle.getFont(100, 1), "Wave " + Spawner.getWave(), Game.W / 2, Game.H / 2);
		}

		if (incR) {
			rotation += 0.1;
			y -= 0.1;
			if (rotation >= 2) {
				incR = false;
				decR = true;
			}
		}

		if (decR) {
			rotation -= 0.1f;
			y += 0.1;
			if (rotation <= -2) {
				decR = false;
				incR = true;
			}
		}

		System.out.println(decR);
		System.out.println(rotation);
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(new Color(0, 0, 0, 100));
		g.fillRect(0, 0, Game.W, Game.H);

		g2.rotate(Math.toRadians(rotation), Game.W / 2 + 50, 100);
		font.draw("CHOSE AN UPGRADE", Game.W / 2 - 130, (int) (100), g, 30, 24);
		g2.rotate(Math.toRadians(-rotation), Game.W / 2 + 50, 100);

		for (int i = 0; i < 3; i++) {
			g.drawImage(opt, Game.W / 2 - 150, (int) ((200 + i * opt.getHeight() * 6)), opt.getWidth() * 6,
					opt.getHeight() * 5, null);
			// g.drawImage(upg, Game.W / 2 - 110, 235 + i * opt.getHeight() * 6, 64, 64,
			// null);
			// font.draw(getStringlength(teste2,0), Game.W / 2 - 50, 235 + i *
			// opt.getHeight() * 6, g, 20, 14);

			upgrade[i].render(g, Game.W / 2 - 120, (int) ((200 + i * opt.getHeight() * 6 + 36) - y));
			upgrade[selected].render2(g, triangle, 430, (int) ((250 + selected * 150 + 10)));
		}

		// System.out.printf("lenght "+teste2.length()+"\n");

	}

	public String testeDividir(String arg) {
		int numeroDeVezes = arg.length() / 23;
		if (arg.length() > 23) {

			for (int i = 0; i < numeroDeVezes; i++) {
				String[] controle = { arg.substring(i + 23, i * 23) };
				return controle[i];
			}

		}

		return null;
	}

	public FontX drawStrings() {

		return null;
	}
}
