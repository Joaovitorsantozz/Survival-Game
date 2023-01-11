package nnrg.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage {
	private BufferedImage img;
	private BufferedImage anim[];
	private int[] pixels;
	private int w,h;
	public LoadImage(String dir) {
		if(dir!=null) {
			try {
				img = ImageIO.read(getClass().getResource(dir));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		w=img.getWidth();
		h=img.getHeight();
		pixels=img.getRGB(0,0,w,h,null,0,w);
		
		img.flush();
	}

	public BufferedImage getImage() {
		return img;
	}

	public BufferedImage[] Cut(int max, int x, int y, int w, int h,BufferedImage img) {
		anim = new BufferedImage[max];
		for (int i = 0; i < max; i++) {
			anim[i] = img.getSubimage(x, y + (i * h), w, h);
		}
		return anim;
	}
	public BufferedImage[] CutHor(int max, int x, int y, int w, int h,BufferedImage img) {
		anim = new BufferedImage[max];
		for (int i = 0; i < max; i++) {
			anim[i] = img.getSubimage(x+(i*w), y, w, h);
		}
		return anim;
	}
	
	public int[] getPixels() {
		return pixels;

	}
	public int getW() {return w;}
	public int getH() {return h;}
}
