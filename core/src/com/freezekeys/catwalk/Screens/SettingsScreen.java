package com.freezekeys.catwalk.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Tools.Settings;

/**
 * Created by Lucas on 25.03.2016.
 */
public class SettingsScreen implements Screen{
    private Catwalk game;

    private Rectangle sfx, music, gamecache, back;
    private OrthographicCamera settingscam;
    private StretchViewport settingsViewport;
    private Vector3 touch;

    public SettingsScreen (Catwalk game) {
        this.game = game;
        settingscam = new OrthographicCamera(Catwalk.V_WIDTH, Catwalk.V_HEIGHT);
        settingscam.position.set(Catwalk.V_WIDTH / 2, Catwalk.V_HEIGHT / 2, 0);
        sfx = new Rectangle((Catwalk.V_WIDTH / 2) -75, (Catwalk.V_HEIGHT / 2) -45 , 200, 60);
        music = new Rectangle((Catwalk.V_WIDTH / 2) -75, (Catwalk.V_HEIGHT / 2) +10, 200, 60);
        back = new Rectangle(10, Catwalk.V_HEIGHT - 70, 60, 60);
        gamecache= new Rectangle(0,0, 200, 80);
        touch = new Vector3();
        settingsViewport = new StretchViewport(Catwalk.V_WIDTH/Catwalk.PPM,Catwalk.V_HEIGHT/Catwalk.PPM, settingscam);
    }

    public void draw()
    {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        settingscam.update();
        game.batch.setProjectionMatrix(settingscam.combined);

        game.batch.disableBlending();
        game.batch.begin();
        Texture background = new Texture("settings.png");
        game.batch.draw(background, 0, 0, 208, 400);
        game.batch.end();
        game.batch.enableBlending();
        game.batch.begin();
        Settings.loadPrefs();
        if (Settings.musicEnabled)
        {
            Texture musicPaw = new Texture("paw.png");
            game.batch.draw(musicPaw,(Catwalk.V_WIDTH /2)+35,(Catwalk.V_HEIGHT / 2) + 10,60, 60);
        }
        if (Settings.sfxEnabled)
        {
            Texture sfxPaw = new Texture("paw.png");
            game.batch.draw(sfxPaw,(Catwalk.V_WIDTH  /2) +35,(Catwalk.V_HEIGHT / 2) -55,60, 60);
        }
        game.batch.end();


    }

    public void update(){
        if (Gdx.input.justTouched()) {
            settingscam.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (music.contains(touch.x, touch.y)) {
                if (Settings.musicEnabled)
                    Settings.musicEnabled = false;
                else {
                    Settings.musicEnabled = true;
                    Settings.muted = false;
                }
                if ((Settings.musicEnabled || Settings.sfxEnabled) == false)
                    Settings.muted = true;
                Settings.savePrefs();
                return;
            }
            if (sfx.contains(touch.x, touch.y)) {
                if (Settings.sfxEnabled)
                    Settings.sfxEnabled = false;
                else {
                    Settings.sfxEnabled = true;
                    Settings.muted = false;
                }
                if ((Settings.musicEnabled || Settings.sfxEnabled) == false)
                    Settings.muted = true;
                Settings.savePrefs();
                return;
            }
            if (back.contains(touch.x, touch.y)){
                game.setScreen(new TitleMenuScreen(game));
                return;
            }

            if (gamecache.contains(touch.x, touch.y))
            {
                Settings.wipeCache();
                System.out.println("Wiped, yo!");
                return;
            }
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
