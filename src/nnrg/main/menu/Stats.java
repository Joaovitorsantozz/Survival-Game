package nnrg.main.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nnrg.gameobject.entitys.Player;
import nnrg.main.LoadImage;
import nnrg.main.others.FontStyle;

public class Stats {
	private Player player;
	
	
	
	private BufferedImage statsheet=new LoadImage("/upg/statsheet.png").getImage();
	
	private BufferedImage stat[]= {statsheet.getSubimage(0, 0,15,15),
			statsheet.getSubimage(0,15,15,15),
			statsheet.getSubimage(0,30,15,15)
			};
	private float [] statvalue=new float[3];
	
	public Stats(Player play) {
		setPlayer(play);
	}

	public void render(Graphics g) {

		//g.setColor(Colors.black);
	//	g.fillRect(20, 200,150,200);
		statvalue[0]=player.speed;
		statvalue[1]=player.damage; 
		statvalue[2]=player.maxStam;
		for(int i=0,n=3;i<n;i++){
			g.drawImage(stat[i],40,200+i*50,48,48,null);
			g.setFont(FontStyle.getFont2(40, 1));
			g.setColor(Color.white);		
			g.drawString(""+statvalue[i],90,235+i*50);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
