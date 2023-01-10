package nnrg.main;

import java.awt.Graphics;

public class FontX {
	public String chars=""+"ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789";
	public SpriteSheet fontsheet=new SpriteSheet("/fontsheet.png");
	public void draw(String msg,int x,int y,Graphics g, int size,int spc) {
		msg=msg.toUpperCase();
		for(int i=0;i<msg.length();i++) {
			int ix=chars.indexOf(msg.charAt(i));
			if(ix>=0) {
				g.drawImage(fontsheet.getSprite(0+ix*50,0,50,45),x+i*spc,y,size,size,null);
			}
		}
	}
}
