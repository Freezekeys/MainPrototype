package com.freezekeys.catwalk.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.freezekeys.catwalk.Catwalk;
import com.sun.scenario.Settings;



/**
 * Created by Lucas on 21.03.2016.
 */
public class TitleMenuScreen implements Screen {
    private Catwalk game;
    private Music music;

    private Rectangle levelselection, customization, settings, mute;
    private OrthographicCamera menucam;
    private StretchViewport titleViewport;
    private Vector3 touch;

    public TitleMenuScreen (Catwalk game) {
        this.game = game;

        menucam = new OrthographicCamera(Catwalk.V_WIDTH, Catwalk.V_HEIGHT);
        menucam.position.set(Catwalk.V_WIDTH / 2, Catwalk.V_HEIGHT / 2, 0);
        mute = new Rectangle(0, 0, 40, 40);
        levelselection = new Rectangle((Catwalk.V_WIDTH / 2) -75, (Catwalk.V_HEIGHT / 2) -30, 150, 60);
        settings = new Rectangle((Catwalk.V_WIDTH / 2) -75, (Catwalk.V_HEIGHT / 2) -80, 150, 60);
        touch = new Vector3();
        titleViewport = new StretchViewport(Catwalk.V_WIDTH/Catwalk.PPM,Catwalk.V_HEIGHT/Catwalk.PPM, menucam);
    }

    public void draw()
    {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        menucam.update();
        game.batch.setProjectionMatrix(menucam.combined);

        game.batch.disableBlending();
        game.batch.begin();
        Texture background = new Texture("bg.png");
        game.batch.draw(background, 0, 0, 208, 400);

        game.batch.end();

        game.batch.enableBlending();
    }

    public void update(){
        if (Gdx.input.justTouched()) {
            menucam.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (levelselection.contains(touch.x, touch.y)) {
                game.setScreen(new PlayScreen(game));
                return;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S))
        {
            game.setScreen(new PlayScreen(game));
            return;
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
