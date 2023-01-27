package nnrg.main.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nnrg.main.HandlerGame;
import nnrg.main.LoadImage;
import nnrg.main.others.FontX;

public class Upgrade {

	private BufferedImage spr;
	private String description;
	private String description2[] = new String[2];
	private FontX font;
	private BufferedImage streength=new LoadImage("/upg/sword.png").getImage(),
			agility=new LoadImage("/upg/agility.png").getImage(),
			stamina=new LoadImage("/upg/stamina.png").getImage();
	private int Id;

	public Upgrade(int ID) {
		font = new FontX();
		this.Id = ID;
		setValue();
	}

	public void setValue() {
		switch (Id) {

		case 1:
			HandlerGame.level.getPlayer().damage += 2;
			setDescription("Improve your streength stats by 5");
			setSpr(streength);
			break;

		case 2:
			HandlerGame.level.getPlayer().speed += 1;
			setDescription("Improve your agility stats by 05");
			setSpr(agility);
			break;

		case 3:
			HandlerGame.level.getPlayer().maxStam+=10;
			setDescription("Improve your stamina stats by 5");
			setSpr(stamina);
			break;

		}
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(spr, x, y, 64, 64, null);

		font.draw(getStringlength(description, 0), x + description.length() * 2, y + 5, g, 20, 14);
		font.draw(getStringlength(description, 1), x + description.length() * 3 + 10, y + 32, g, 20, 14);

	}

	public void render2(Graphics g, BufferedImage spr, int x, int y) {
		g.drawImage(spr, x, y, 32, 32, null);
	}

	public String getStringlength(String t1, int index) {
		// separa a string apartir de um espaço vazio, evitando separar ela no meio de
		// uma palavra
		final int tamanho = (int) (t1.length() * 0.5);
		if (t1.charAt(tamanho) == ' ') {
			String[] controle = { t1.substring(0, tamanho), t1.substring(t1.length(), t1.length()) };
			description2 = controle;
			return controle[index];
		} else {
			for (int i = tamanho; i < t1.length(); i++) {
				if (t1.charAt(i) == ' ') {
					String[] controle = { t1.substring(0, i), t1.substring(i, t1.length()) };
					description2 = controle;
					return controle[index];
				}
			}
		}

		return "";
		// começo da string e final
	}

	public String getdes() {
		return this.description;
	}

	public BufferedImage getSpr() {
		return spr;
	}

	public void setSpr(BufferedImage spr) {
		this.spr = spr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
