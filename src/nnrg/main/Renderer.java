package nnrg.main;

import java.awt.image.DataBufferInt;

public class Renderer {
private int pw,ph;
	
	private int[] p ;
	public Renderer(Game gc) {
		pw=Game.W;
		ph=Game.H;
		
		p=((DataBufferInt)gc.getWindows().getImage().getRaster().getDataBuffer()).getData();
		
	}
	
	
	public void clear() {
		for(int i=0;i<p.length;i++) {
			p[i]=0;
		}
	}
	
	public void setPixel(int x,int y, int value) {
		if(x<0||x>=pw||y<0||y>=ph) {
			return;
		}
		p[x+y*pw]=value;
	}
	public void drawImage(Image image,int offX,int offY) {
		for (int y=0;y<image.getH();y++) {	
			for (int x=0;x<image.getW();x++) {
				setPixel(x+offX,y+offY,image.getP()[x+y*image.getW()]);
			}
		}
	}
}
