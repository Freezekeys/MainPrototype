package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Misc;
import com.freezekeys.catwalk.Screens.PlayScreen;

import java.util.Random;

/**
 * Created by xrans on 3/24/2016.
 */
public class Dog extends Enemy{

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;
    private boolean left = true;
    private PlayScreen screen;

    public Dog(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        this.screen = screen;
        fixture.setUserData(this);
        setCategoryFilter(Catwalk.ENEMY_BIT);

        frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("dog"),i*32,0, 32 , 32));
        }
        walkAnimation = new Animation(0.1f,frames);
        stateTime = 0;
        setBounds(getX(), getY(), 32 / Catwalk.PPM, 32 / Catwalk.PPM);
    }

    private int directionCount = 0;
    private int limit = 0;

    @Override
    public void update(float dt){
        stateTime+=dt;
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        setRegion(walkAnimation.getKeyFrame(stateTime, true));

        if(left){
            body.setLinearVelocity(-0.5f,0);
        }else{
            body.setLinearVelocity(0.5f,0);
        }
        directionCount++;
        if(directionCount > limit){
            limit = new Random().nextInt(150);
            int direction = new Random().nextInt(2);
            if(direction>0) left=false;
            else left = true;
            directionCount = 0;
        }

    }

    @Override
    public void onBodyHit() {
        screen.gameOver(Misc.STATUS_EATEN, !Misc.WIN);
    }

    @Override
    protected void defineEnemy(float x, float y) {
        BodyDef bdef = new BodyDef();

        bdef.position.set(x / Catwalk.PPM, y / Catwalk.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Catwalk.PPM);

        fdef.filter.categoryBits = Catwalk.ENEMY_BIT;
        fdef.filter.maskBits = Catwalk.GROUND_BIT | Catwalk.POWERUP_BIT
                | Catwalk.WALL_BIT | Catwalk.ENEMY_BIT | Catwalk.OBJECT_BIT | Catwalk.CAT_BIT;

        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }
}
