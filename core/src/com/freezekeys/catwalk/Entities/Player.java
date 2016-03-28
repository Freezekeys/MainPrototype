package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Screens.PlayScreen;


/**
 * Created by xrans on 3/9/2016.
 */
public class Player extends Sprite {

    protected World world;
    public Body b2body;
    private TextureRegion catNormal;

    public enum State {RUNNING, LEFT, RIGHT}

    public State currentState;
    public State previousState;
    private Animation catRun;
    private Animation catLeft;
    private Animation catRight;
    private float stateTimer;

    public Player(PlayScreen screen) {
        super(screen.getAtlas().findRegion("cat"));
        this.world = screen.getWorld();

        currentState = State.RUNNING;
        previousState = State.RUNNING;
        stateTimer = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        /* Running */
        for (int i = 0; i < 3; i++)
            frames.add(new TextureRegion(getTexture(),4*32+ i * 32, 3*32, 32, 32));
        catRun = new Animation(0.1f, frames);
        frames.clear();

        /* Left */
        for (int i = 0; i < 3; i++)
            frames.add(new TextureRegion(getTexture(),4*32+ i * 32, 1*32, 32, 32));
        catLeft = new Animation(0.1f, frames);
        frames.clear();

        /* Right */
        for (int i = 0; i < 3; i++)
            frames.add(new TextureRegion(getTexture(),4*32+ i * 32, 2*32, 32, 32));
        catRight = new Animation(0.1f, frames);
        frames.clear();

        definePlayer(screen);
        catNormal = new TextureRegion(getTexture(), 66, 0, 32, 32);
        setBounds(0, 0, 32 / Catwalk.PPM, 32 / Catwalk.PPM);
        setRegion(catNormal);
    }

    public void definePlayer(PlayScreen screen) {
        BodyDef bdef = new BodyDef();

        bdef.position.set(screen.getCreator().getLevelStart().x / Catwalk.PPM,
                screen.getCreator().getLevelStart().y / Catwalk.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Catwalk.PPM);

        fdef.filter.categoryBits = Catwalk.CAT_BIT;
        fdef.filter.maskBits = Catwalk.GROUND_BIT | Catwalk.POWERUP_BIT | Catwalk.WALL_BIT |
                Catwalk.END_BIT | Catwalk.ENEMY_BIT;

        fdef.shape = shape;

        // Body collisions
        b2body.createFixture(fdef).setUserData("body");

        // Head collisions
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / Catwalk.PPM, 7 / Catwalk.PPM), new Vector2(2 / Catwalk.PPM, 7 /
                Catwalk.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head");
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;
        switch(currentState) {
            case LEFT:
                region = catLeft.getKeyFrame(stateTimer,true);
                break;
            case RIGHT:
                region = catRight.getKeyFrame(stateTimer,true);
                break;
            default:
                region = catRun.getKeyFrame(stateTimer,true);
                break;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if (b2body.getLinearVelocity().x > 0) return State.RIGHT;
        else if (b2body.getLinearVelocity().x < 0) return State.LEFT;
        else return State.RUNNING;
    }
}