package com.freezekeys.catwalk.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
public class SelectScreen implements Screen {
    private Catwalk game;

    private Rectangle one, two, three, four, back;
    private OrthographicCamera selectcam;
    private StretchViewport selectViewport;
    private Vector3 touch;

    public SelectScreen (Catwalk game) {
        this.game = game;
        selectcam = new OrthographicCamera(Catwalk.V_WIDTH, Catwalk.V_HEIGHT);
        selectcam.position.set(Catwalk.V_WIDTH / 2, Catwalk.V_HEIGHT / 2, 0);
        back = new Rectangle(10, Catwalk.V_HEIGHT - 70, 60, 60);
        one = new Rectangle((Catwalk.V_WIDTH /2) + 10, (Catwalk.V_HEIGHT /2) +30, 80, 80);
        two = new Rectangle(10, (Catwalk.V_HEIGHT /2)-35, 80, 80);
        three = new Rectangle((Catwalk.V_WIDTH /2) + 10, (Catwalk.V_HEIGHT /2) -95, 80, 80);
        four = new Rectangle(10, (Catwalk.V_HEIGHT /2) -165, 80, 80);
        touch = new Vector3();
        selectViewport = new StretchViewport(Catwalk.V_WIDTH/Catwalk.PPM,Catwalk.V_HEIGHT/Catwalk.PPM, selectcam);
    }

    public void draw()
    {
        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        selectcam.update();
        game.batch.setProjectionMatrix(selectcam.combined);

        game.batch.disableBlending();
        game.batch.begin();
        Texture background = new Texture("levelselect.png");
        game.batch.draw(background, 0, 0, 208, 400);
        game.batch.end();
        game.batch.enableBlending();
        game.batch.begin();
        game.batch.end();


    }

    public void update(){
        if (Gdx.input.justTouched()) {
            selectcam.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (back.contains(touch.x, touch.y)){
                game.setScreen(new TitleMenuScreen(game));
                return;
            }

            if (one.contains(touch.x, touch.y)){
                game.setScreen(new PlayScreen(game, 1));
                return;
            }
            if (two.contains(touch.x, touch.y)){
                System.out.println("hit2");
                //game.setScreen(new PlayScreen(game, 1));
                return;
            }
            if (three.contains(touch.x, touch.y)){
                //game.setScreen(new PlayScreen(game, 1));
                System.out.println("hit3");
                return;
            }
            if (four.contains(touch.x, touch.y)){
                //game.setScreen(new PlayScreen(game, 1));
                System.out.println("hit4");
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
