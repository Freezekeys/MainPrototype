package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Screens.PlayScreen;


/**
 * Created by xrans on 3/9/2016.
 */
public class Player extends Sprite{

    protected World world;
    public Body b2body;
    private TextureRegion catNormal;

    public Player(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("bat_32x32"));
        this.world = world;
        definePlayer();
        catNormal = new TextureRegion(getTexture(),0 ,0, 32, 32);
        setBounds(0,0,32/Catwalk.PPM,32/Catwalk.PPM);
        setRegion(catNormal);
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();

        bdef.position.set(32 / Catwalk.PPM,32 / Catwalk.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body= world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Catwalk.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y -getHeight()/2);
    }
}
