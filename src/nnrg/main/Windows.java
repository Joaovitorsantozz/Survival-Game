package nnrg.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Windows {
	private JFrame jframe;
	private  BufferedImage image;
	private Canvas canvas;
	private BufferStrategy bs;
	private Graphics g;
	private Game gc;
	public Windows(Game gc) {
		this.gc=gc;
		int width=Game.W;
		int height=Game.H;
		image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		
		jframe = new JFrame("title");
		jframe.setPreferredSize(new Dimension(width, height));
		jframe.setMaximumSize(new Dimension(width, height));
		jframe.setMinimumSize(new Dimension(width, height));
		jframe.add(gc, BorderLayout.CENTER);
		jframe.setUndecorated(true);
		jframe.setResizable(true);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		
		
		
	}
	
	
	
	public void update() {
		
		//para desenhar usando o metodo de pixel precisa da imagem

	}



	public BufferedImage getImage() {
		return image;
	}



	public void setImage(BufferedImage image) {
		this.image = image;
	}



	public Canvas getCanvas() {
		return canvas;
	}



	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
}
