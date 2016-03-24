package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.graphics.Texture;
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

    public enum State {RUNNING, JUMPING, LEFT, RIGHT}

    public State currentState;
    public State previousState;
    private Animation catRun;
    private Animation catJump;
    private Animation catLeft;


    private float stateTimer;
    private int direction;

    public Player(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("bat_32x32"));
        this.world = world;

        currentState = State.RUNNING;
        previousState = State.RUNNING;
        stateTimer = 0;
        direction = 0;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        /* Running */
        for (int i = 0; i < 3; i++)
            frames.add(new TextureRegion(getTexture(), 32 + i * 32, 64, 32, 32));
        catRun = new Animation(0.1f, frames);
        frames.clear();

        /* Left */
        for (int i = 0; i < 3; i++)
            frames.add(new TextureRegion(getTexture(), 32 + i * 32, 96, 32, 32));
        catLeft = new Animation(0.1f, frames);
        frames.clear();

        /* Jump */
        for (int i = 0; i < 1; i++)
            frames.add(new TextureRegion(getTexture(), i * 32, 96, 32, 32));
        catJump = new Animation(0.1f, frames);
        frames.clear();

        definePlayer();
        catNormal = new TextureRegion(getTexture(), 66, 0, 32, 32);
        setBounds(0, 0, 32 / Catwalk.PPM, 32 / Catwalk.PPM);
        setRegion(catNormal);
    }

    public void definePlayer() {
        BodyDef bdef = new BodyDef();

        bdef.position.set(32 / Catwalk.PPM, 32 / Catwalk.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Catwalk.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("head");

        // If we want special collision for head edge
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2 / Catwalk.PPM, 7 / Catwalk.PPM), new Vector2(2 / Catwalk.PPM, 7 /
                Catwalk.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        //b2body.createFixture(fdef).setUserData("head");

    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);

        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;
        switch(currentState){
            case LEFT: region =  catLeft.getKeyFrame(stateTimer);
                break;
            case RIGHT: region = catLeft.getKeyFrame(stateTimer);
                break;
            default: region = catRun.getKeyFrame(stateTimer);
                break;
        }

        /* Directions NEED some FIXING
        * 0 = running forward
        * 1 = running left
        * 2 = running right
        */
        if(b2body.getLinearVelocity().x < 0 && !region.isFlipX()){
            region.flip(true, false);
            direction = 1;
        }
        else if(b2body.getLinearVelocity().y > 0 || direction == 0){
            direction = 0;
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