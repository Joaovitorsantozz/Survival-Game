package nnrg.main.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nnrg.main.FontX;
import nnrg.main.Game;
import nnrg.main.LoadImage;

public class Upgradescreen extends Menu {
	private BufferedImage opt = new LoadImage("/upg/upgscreen.png").getImage(),
			upg = new LoadImage("/upg/sword.png").getImage();
	private FontX font;
	public String teste = "Streength";
	public String teste2="Improve your streength ";
	public String teste3 = "stats by 5 ";

	public Upgradescreen() {
		font=new FontX();
	}

	public void render(Graphics g) {
		font.draw("CHOSE AN UPGRADE",Game.W/2-130,100, g,30,24);
		Math.abs(0);
		for (int i = 0; i < 3; i++) {
			g.drawImage(opt,Game.W/2-150,200+i*opt.getHeight()*6,opt.getWidth()*6,opt.getHeight()*5,null);
			g.drawImage(upg,Game.W/2-110,235+i*opt.getHeight()*6,64,64,null);
		}
	}
}
