package nnrg.main.menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nnrg.main.Game;
import nnrg.main.LoadImage;
import nnrg.main.others.FontX;

public class Upgradescreen extends Menu {
	private BufferedImage opt = new LoadImage("/upg/upgscreen.png").getImage(),
			upg = new LoadImage("/upg/sword.png").getImage();
	private FontX font;

	public String teste2 = "Improve your streength stats by 5";

	public String[] teste4;

	public Upgradescreen() {
		font = new FontX();
	}

	public void render(Graphics g) {
		font.draw("CHOSE AN UPGRADE", Game.W / 2 - 130, 100, g, 30, 24);

		for (int i = 0; i < 3; i++) {
			g.drawImage(opt, Game.W / 2 - 150, 200 + i * opt.getHeight() * 6, opt.getWidth() * 6, opt.getHeight() * 5,
					null);
			g.drawImage(upg, Game.W / 2 - 110, 235 + i * opt.getHeight() * 6, 64, 64, null);
			font.draw(getStringlength(teste2,0), Game.W / 2 - 50, 235 + i * opt.getHeight() * 6, g, 20, 14);
			
		}

		// System.out.printf("lenght "+teste2.length()+"\n");
	}

	public String getStringlength(String t1, int index) {
	
		if(t1.charAt(t1.length()/2)==' ') {
			String[] controle= {t1.substring(0,t1.length()/2),t1.substring(t1.length(),t1.length())};
			return controle[index];
		}else {
			for (int i=t1.length()/2;i<t1.length();i++) {
				if(t1.charAt(i)==' ') {
					String[] controle= {t1.substring(0,i),t1.substring(i,t1.length())};
					return controle[index];
				}
			}
		}

		return"";
		// começo da string e final
	}

}
