package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.freezekeys.catwalk.Catwalk;


/**
 * Created by xrans on 3/16/2016.
 */
public abstract class Interactive {
    protected Fixture fixture;
    protected World world;
    protected TiledMap map;
    protected Body body;


    public Interactive(World world, TiledMap map, Rectangle rect) {
        this.world = world;
        this.map = map;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth()/2)/ Catwalk.PPM, (rect.getY() + rect.getHeight()/2)/Catwalk.PPM);

        body = world.createBody(bdef);
        shape.setAsBox((rect.getWidth()/2)/Catwalk.PPM, (rect.getHeight()/2)/Catwalk.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);

        fixture = body.createFixture(fdef);
    }

    public abstract void onHeadHit();
}
