
package nnrg.main;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import nnrg.gameobjects.GameObject;
import nnrg.gameobjects.GameObjectHandler;
import nnrg.gameobjects.ID;

public class KeyInput implements KeyListener, FocusListener {
	//Old KeyInput Class
	GameObjectHandler handler;
	public static boolean[] press = new boolean[65536];
	KeyEvent ke;

	public KeyInput(GameObjectHandler hand) {
		this.handler = hand;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int b = e.getKeyCode();
		ke = e;
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject ee = handler.object.get(i);
			if (ee.getId() == ID.Player) {

				if (b == KeyEvent.VK_W)
					handler.setUp(true);
				if (b == KeyEvent.VK_S)
					handler.setDown(true);
				if (b == KeyEvent.VK_D)
					handler.setRight(true);
				if (b == KeyEvent.VK_A)
					handler.setLeft(true);
				if (b == KeyEvent.VK_C)
					handler.setAttack(true);
			}
		}
		Arrays.fill(press, true);
	}

	public void keyReleased(KeyEvent e) {
		int b = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject ee = handler.object.get(i);
			if (ee.getId() == ID.Player) {
				if (b == KeyEvent.VK_W)
					handler.setUp(false);
				if (b == KeyEvent.VK_S)
					handler.setDown(false);
				if (b == KeyEvent.VK_D)
					handler.setRight(false);
				if (b == KeyEvent.VK_A)
					handler.setLeft(false);
			
			}
		}
		Arrays.fill(press, false);
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject ee = handler.object.get(i);
			if (ee.getId() == ID.Player) {
				handler.setUp(false);
				handler.setDown(false);
				handler.setRight(false);
				handler.setLeft(false);
			}
		}
	}
}
