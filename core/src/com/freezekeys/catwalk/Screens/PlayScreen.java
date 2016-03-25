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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Entities.Player;
import com.freezekeys.catwalk.Scenes.Hud;
import com.freezekeys.catwalk.Tools.B2WorldCreator;
import com.freezekeys.catwalk.Tools.WorldContactListener;

/**
 * Created by xrans on 3/6/2016.
 */
public class PlayScreen implements Screen{
    private Catwalk game;
    private StretchViewport gamePort;
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
        gamePort = new StretchViewport(Catwalk.V_WIDTH/Catwalk.PPM,Catwalk.V_HEIGHT/Catwalk.PPM,
                gamecam);

        /* create a game HUD for score and timer */
        hud = new Hud(game.batch);

        /* Load our map and setup a map renderer */
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level/testLevelClosed.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Catwalk.PPM);

        /* sets the initial position of a gamecam */
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2, 0);

        world = new World(new Vector2(0,-1/Catwalk.PPM),true);
        b2dr = new Box2DDebugRenderer();

        player = new Player(world,this);

        /* Music setup, sets the music file (that is already loaded) looping continously */
        music = Catwalk.manager.get("audio/music/catwalk_music.ogg", Music.class);
        music.setLooping(true);
        //music.play(); //Sets the music loop playing

        /* create world and player */
        new B2WorldCreator(world, map);

        /* creates a collision detector */
        world.setContactListener(new WorldContactListener());
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    /* Process input from player, hold down mouse, screen speeds up */
    public void handleInput(float dt){

        /* Motion controls */
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && player.b2body.getLinearVelocity().y <= 1)
            player.b2body.applyLinearImpulse(new Vector2(0, 0.5f), player.b2body.getWorldCenter(), true);
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.b2body.getLinearVelocity().y >= -1)
            player.b2body.applyLinearImpulse(new Vector2(0, -0.5f), player.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 1)
            player.b2body.applyLinearImpulse(new Vector2(0.5f, 0), player.b2body.getWorldCenter(), true);
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -1)
            player.b2body.applyLinearImpulse(new Vector2(-0.5f,0), player.b2body.getWorldCenter(), true);

        /* If player is moving, play this shit */
        if(!player.b2body.getLinearVelocity().isZero()) Catwalk.manager.get("audio/sound/catwalk_run.ogg", Sound.class).play();

                /* Gyro Control Portrait*/
        float accX = Gdx.input.getAccelerometerX();
        float accZ = Gdx.input.getAccelerometerZ();
        if(accX < -0.3f && player.b2body.getLinearVelocity().x <= 1)
            player.b2body.applyLinearImpulse(new Vector2(1f, 0), player.b2body.getWorldCenter(), true);
        else if(accX > 0.3f && player.b2body.getLinearVelocity().x >= -1)
            player.b2body.applyLinearImpulse(new Vector2(-1f, 0), player.b2body.getWorldCenter(), true);
        if(accZ < 3.5f && player.b2body.getLinearVelocity().y >= -1)
            player.b2body.applyLinearImpulse(new Vector2(0, -0.5f), player.b2body.getWorldCenter(), true);
        else if( accZ > 4.5f && player.b2body.getLinearVelocity().y <= 1)
            player.b2body.applyLinearImpulse(new Vector2(0, 0.5f), player.b2body.getWorldCenter(), true);

        /* Auto slowing down */
        if(player.b2body.getLinearVelocity().x > 0)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f,0),player.b2body.getWorldCenter(), true);
        else if(player.b2body.getLinearVelocity().x < 0)
            player.b2body.applyLinearImpulse(new Vector2(0.1f,0),player.b2body.getWorldCenter(), true);
        if(player.b2body.getLinearVelocity().y > 0)
            player.b2body.applyLinearImpulse(new Vector2(0,-0.1f),player.b2body.getWorldCenter(), true);
        else if(player.b2body.getLinearVelocity().y < 0)
            player.b2body.applyLinearImpulse(new Vector2(0,0.1f),player.b2body.getWorldCenter(), true);

        //gamecam.position.y += 100/Catwalk.PPM * dt; //gamecam moves on its own

        if(player.getY() > gamePort.getWorldHeight()/2)
            gamecam.position.y = player.getY(); //gamecam moves with player

        //player moves with camera
        /*
        if(player.b2body.getLinearVelocity().y <= 0.5f)
            player.b2body.applyLinearImpulse(new Vector2(0f,0.5f),player.b2body.getWorldCenter(), true);
        */

    }

    /* Simple update method, each frame it executes everything below */
    public void update(float dt){
        handleInput(dt);

        world.step(1 / 60f, 6, 2); //collision calculation precision, higher number - more precision.
        player.update(dt);

        gamecam.update();

        renderer.setView(gamecam);
    }

    /* Main screen rendering method */
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1); //sets the background color too
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // render game map
        renderer.render();

        // debug lines, will be removed for release
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
