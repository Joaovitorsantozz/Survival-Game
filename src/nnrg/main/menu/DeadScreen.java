package nnrg.main.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import nnrg.main.Game;
import nnrg.main.InputHandler;
import nnrg.main.LoadImage;
import nnrg.main.others.FontStyle;
import nnrg.main.others.FontX;

public class DeadScreen extends Menu{
	private FontX font;
	private BufferedImage select=new LoadImage("/menu/triangle.png").getImage(),back=new LoadImage("/upg/upgscreen.png").getImage();
	
	private int selected=520;
	public DeadScreen(InputHandler inpt){
		font=new FontX();
		this.input=inpt;
	}
	public void tick() {
		super.tick();
		
		
		if(input.right.down) {
			input.right.down=false;
			System.out.println("TA APERTANDO CARALHO");
			selected=630;
		}
		if(input.left.down) {
			input.left.down=false;
			selected=520;
		}
	}
	public void render(Graphics g) {
		g.setColor(new Color(255,0,0,50));
		g.fillRect(0, 0,Game.W,Game.H);
		
		g.drawImage(back,230,180,back.getWidth()*10,back.getHeight()*15,null);
		
		font.draw("YOU DIED",Game.W/2-300,Game.H/2-100, g,100, 65);
		
		g.setColor(Color.white);
		g.setFont(FontStyle.getFont2(60,1));
		//g.drawString("Want to play again?",Game.W/2-270,500);
		font.draw("Continue",Game.W/2-180,Game.H/2+50, g,50, 35);
		
		font.draw("Yes",Game.W/2-120,Game.H/2+120, g,30, 20);
		font.draw("No",Game.W/2,Game.H/2+120, g,30, 20);
		
		g.drawImage(select,selected,Game.H/2+160,null);
	}
}
