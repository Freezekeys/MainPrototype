package com.freezekeys.catwalk;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.freezekeys.catwalk.Screens.PlayScreen;

public class Catwalk extends Game {
	public SpriteBatch batch;

	/* Declaration of screen properties, from here they are used everywhere else */
	public static final int V_WIDTH=208;
	public static final int V_HEIGHT=400;

	/* Pixel per m resolution */
	public static final float PPM = 100;

	/* For loading audio files */
	public static AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/catwalk_music.ogg",Music.class);
		manager.load("audio/sound/catwalk_run.ogg", Sound.class);
		manager.finishLoading(); //Can be done asynchronous

		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();

	}
}
