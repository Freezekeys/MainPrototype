package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Screens.PlayScreen;

/**
 * Created by xrans on 3/24/2016.
 */
public class Dog extends Enemy{

    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;


    public Dog(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<TextureRegion>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("dog"),i*32,0, 32 , 32));
        }
        walkAnimation = new Animation(0.1f,frames);
        stateTime = 0;
        setBounds(getX(), getY(), 32 / Catwalk.PPM, 32 / Catwalk.PPM);
    }

    public void update(float dt){
        stateTime+=dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y-getHeight()/2);
        setRegion(walkAnimation.getKeyFrame(stateTime,true));
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();

        bdef.position.set(32 / Catwalk.PPM, 32 / Catwalk.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Catwalk.PPM);

        fdef.filter.categoryBits = Catwalk.ENEMY_BIT;
        fdef.filter.maskBits = Catwalk.GROUND_BIT | Catwalk.POWERUP_BIT
                | Catwalk.WALL_BIT | Catwalk.ENEMY_BIT |
                Catwalk.OBJECT_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }
}
