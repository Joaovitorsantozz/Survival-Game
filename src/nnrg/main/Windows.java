package nnrg.main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Windows {
	private static final long serialVersionUID = 1L;
	public JFrame jframe;
	
	public BufferedImage image;
	public Game game;
	
	
	private int[] pixels;
	public Windows(int width, int height, String name, Game game) {
	
		image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		jframe = new JFrame(name);
		jframe.setPreferredSize(new Dimension(width, height));
		jframe.setMaximumSize(new Dimension(width, height));
		jframe.setMinimumSize(new Dimension(width, height));
		jframe.add(game, BorderLayout.CENTER);
		jframe.setUndecorated(true);
		jframe.setResizable(true);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		
		pixels=((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	}
	
	
	public void clear() {
		for(int i=0;i<pixels.length;i++) {
			pixels[i]+=i;
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
}
