package com.HagekopStudios.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GetsInput implements KeyListener {

	public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attack = new Key();
	public Key Menu = new Key();

	public class Key {
		public int presses, absorbs;
		public boolean down, clicked;

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
			if (pressed) {
				presses++;
			}
		}

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public GetsInput(Game game) {
		game.addKeyListener(this);
	}
	public void keyPressed(KeyEvent ke) {
		toggle(ke,true);
	}
	public void toggle(KeyEvent ke, boolean pressed) {
		
		if(ke.getKeyCode() == KeyEvent.VK_NUMPAD2) down.toggle(pressed);
		if(ke.getKeyCode() == KeyEvent.VK_S) down.toggle(pressed);
		
		if(ke.getKeyCode() == KeyEvent.VK_NUMPAD4) left.toggle(pressed);
		if(ke.getKeyCode() == KeyEvent.VK_A) left.toggle(pressed);
		
		if(ke.getKeyCode() == KeyEvent.VK_NUMPAD6) right.toggle(pressed);
		if(ke.getKeyCode() == KeyEvent.VK_D) right.toggle(pressed);
		
		if(ke.getKeyCode() == KeyEvent.VK_NUMPAD8) up.toggle(pressed);
		if(ke.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
		
		if(ke.getKeyCode() == KeyEvent.VK_E) Menu.toggle(pressed);
		
		if(ke.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
		
	}

	public void keyReleased(KeyEvent ke) {
		toggle(ke, false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
