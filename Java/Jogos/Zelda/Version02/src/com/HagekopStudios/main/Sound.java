package com.HagekopStudios.main;

import java.applet.Applet;
import java.applet.AudioClip;


public class Sound {
	private AudioClip clip;
	
	public static final Sound musicBackground = new Sound("musics/MainMusic.wav");
	public static final Sound musicGameBackground = new Sound("musics/GameMusic.wav");
	public static final Sound shoot = new Sound("Efects/Shoot.wav");
	public static final Sound DamegePlayer = new Sound("Efects/DamegePlayer.wav");
	public static final Sound DamegeEnemy = new Sound("Efects/DamegeEnemy.wav");
	
	private Sound(String name) {
		
		try {
	
			clip = 	Applet.newAudioClip(Sound.class.getResource("/Sounds/"+name));
	
		}catch(Throwable e) {
	
			e.printStackTrace();
		
		}
	}
	
	public void play() {
	
		try {
		
			new Thread() {
			
				public void run() {
				
					clip.play();
				
				}
			}.start();
		
		}catch(Throwable e) {
		
			e.printStackTrace();
		
		}
	}
	
	public void loop() {
		
		try {
	
			new Thread() {
			
				public void run() {
				
					clip.loop();
				
				}
			}.start();
		
		}catch(Throwable e) {
		
			e.printStackTrace();
		
		}
	}
	
	public void stop() {
	
		try {
		
			new Thread() {
			
				public void run() {
				
					clip.stop();
		
				}
			}.start();
	
		}catch(Throwable e) {
		
			e.printStackTrace();
		
		}
	}
}
