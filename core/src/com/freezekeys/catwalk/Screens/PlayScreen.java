package com.freezekeys.catwalk.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Entities.Player;
import com.freezekeys.catwalk.Scenes.Hud;
import com.freezekeys.catwalk.Tools.B2WorldCreator;

/**
 * Created by xrans on 3/6/2016.
 */
public class PlayScreen implements Screen{
    private Catwalk game;
    private FitViewport gamePort;
    private OrthographicCamera gamecam;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Player player;

    private Music music;
    private TextureAtlas atlas;


    public PlayScreen(Catwalk game){
        atlas = new TextureAtlas("bat.pack");

        /* create main instance of the game */
        this.game = game;

        /* create cam used to scroll vertically */
        gamecam = new OrthographicCamera();

        /* create a FitViewport to maintain virtual aspect ratio  */
        gamePort = new FitViewport(Catwalk.V_WIDTH/Catwalk.PPM,Catwalk.V_HEIGHT/Catwalk.PPM, gamecam);

        /* create a game HUD for score and timer */
        hud = new Hud(game.batch);

        /* Load our map and setup a map renderer */
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("./level/testLevel.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Catwalk.PPM);

        /* sets the initial position of a gamecam */
        gamecam.position.set(gamePort.getWorldWidth() / 2 - 0.9f, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0,-1/Catwalk.PPM),true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world,this);

        /* Music setup, sets the music file (that is already loaded) looping continously */
        music = Catwalk.manager.get("audio/music/catwalk_music.ogg", Music.class);
        music.setLooping(true);
        music.play();

        /* create world and player */
        new B2WorldCreator(world, map);
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    /* Process input from player, hold down mouse, screen speeds up */
    public void handleInput(float dt){

        if(Gdx.input.isKeyPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= 1)
            player.b2body.applyLinearImpulse(new Vector2(0, 0.5f), player.b2body.getWorldCenter(), true);
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.b2body.getLinearVelocity().y >= -1)
            player.b2body.applyLinearImpulse(new Vector2(0, -0.5f), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 1)
            player.b2body.applyLinearImpulse(new Vector2(0.5f, 0), player.b2body.getWorldCenter(), true);
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -1)
            player.b2body.applyLinearImpulse(new Vector2(-0.5f,0), player.b2body.getWorldCenter(), true);


        if(!player.b2body.getLinearVelocity().isZero()) Catwalk.manager.get("audio/sound/catwalk_run.ogg", Sound.class).play();

        if(player.b2body.getLinearVelocity().x > 0)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2body.getWorldCenter(), true);
        else if(player.b2body.getLinearVelocity().x < 0)
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0),player.b2body.getWorldCenter(), true);
        if(player.b2body.getLinearVelocity().y < 0)
            player.b2body.applyLinearImpulse(new Vector2(0f,0.1f),player.b2body.getWorldCenter(), true);
        else if(player.b2body.getLinearVelocity().y > 0)
            player.b2body.applyLinearImpulse(new Vector2(0,-0.1f),player.b2body.getWorldCenter(), true);


        gamecam.position.y += 100/Catwalk.PPM * dt;

        player.setPosition(player.getX(),player.getY() + 100);
        System.out.println(player.getY());
    }

    public void update(float dt){
        handleInput(dt);

        world.step(1 / 60f, 6, 2); //collision calculation precision, higher number - more precision.
        player.update(dt);

        gamecam.update();


        renderer.setView(gamecam);
    }

    //Main screen rendering method
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render game map
        renderer.render();

        // debug lines
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width,height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
