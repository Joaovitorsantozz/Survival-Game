package nnrg.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

public class Image {
	private int w,h;
	private int[]p;
	
	
	public Image(String path) {
		BufferedImage image =null;
		
		try {
			image=ImageIO.read(Image.class.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		w=image.getWidth();
		h=image.getHeight();
		
		p=image.getRGB(0,0,w,h,null,0, w);
			
		image.flush();
	}
	

	public int getW() {return this.w;}
	public int getH() {return this.h;}
	public int[] getP() {return this.p;}
}
