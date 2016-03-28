package com.freezekeys.catwalk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freezekeys.catwalk.Screens.PlayScreen;
import com.freezekeys.catwalk.Screens.TitleMenuScreen;
import com.freezekeys.catwalk.Tools.Settings;

import java.util.Random;

public class Catwalk extends Game {
	public SpriteBatch batch;

	/* Declaration of screen properties, from here they are used everywhere else */
	public static final int V_WIDTH=192;
	public static final int V_HEIGHT=400;

	public static final short GROUND_BIT = 1;
	public static final short CAT_BIT = 2;
	public static final short WALL_BIT = 4;
	public static final short POWERUP_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short END_BIT = 128;


	/* Pixel per m resolution */
	public static final float PPM = 100;

	/* For loading audio files */
	public static AssetManager manager;

	private Music menuMusic;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        createMusicAsset();
		setScreen(new TitleMenuScreen(this));
		Settings.loadPrefs();
		menuMusic = manager.get("audio/music/catwalk_music.ogg", Music.class);
		menuMusic.setLooping(true);

		if(Settings.musicEnabled) {
			menuMusic().play();
		}
	}

	public Music menuMusic(){
		return menuMusic;
	}

	public void playMusic(){
		if(Settings.musicEnabled)
			menuMusic().play();
	}

	public void createMusicAsset(){
		manager = new AssetManager();
		manager.load("audio/music/catwalk_music.ogg",Music.class);
		manager.load("audio/music/catwalk_music1.wav",Music.class);
		manager.load("audio/music/catwalk_music3.wav",Music.class);

		manager.load("audio/sound/catwalk_run.ogg", Sound.class);
		manager.load("audio/sound/catwalk_pickup.wav", Sound.class);
		manager.load("audio/sound/catwalk_meow.wav", Sound.class);
		manager.load("audio/sound/catwalk_purr.wav", Sound.class);
		manager.load("audio/sound/catwalk_wilhelm.wav", Sound.class);
		manager.finishLoading(); //Can be done asynchronous
	}


	public static Music getMusicForLevel(){
		switch(new Random().nextInt(3)){
			case 0:
				return manager.get("audio/music/catwalk_music.ogg", Music.class);
			case 1:
				return manager.get("audio/music/catwalk_music1.wav", Music.class);
			case 2:
				return manager.get("audio/music/catwalk_music3.wav", Music.class);
		}
		return null;

	}

	@Override
	public void render () {
		super.render();

	}

    public void endGame(){
        this.pause();
        batch.dispose();
        manager.dispose();
        this.dispose();
    }
}
