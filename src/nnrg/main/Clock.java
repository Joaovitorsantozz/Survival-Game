package nnrg.main;

import java.awt.Graphics;

import nnrg.interfaces.Renderable;
import nnrg.interfaces.Tickable;

public class Clock implements Tickable, Renderable {
	private int minutes,seconds;
	private String time = "";

	@Override
	public void render(Graphics g) {
	
		
		// TODO Auto-generated method stub
		g.setFont(FontStyle.getFont2(40, 1));
		g.drawString(time, Game.W / 2, 50);
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		seconds++;
		if (seconds == 60 * 60) {
			minutes++;
			seconds = 0;
		}
		if (seconds < 60*10) {
			time = minutes+":0" + seconds/60;
		} else if(seconds>60*9)  {
			time =minutes+ ":" + seconds/60;
		}
		if(minutes>1) {
			time= minutes+ ":"+seconds/60;
		}
	}

}
