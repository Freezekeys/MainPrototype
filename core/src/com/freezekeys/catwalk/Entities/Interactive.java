package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Screens.PlayScreen;


/**
 * Created by xrans on 3/16/2016.
 */
public abstract class Interactive {
    protected Fixture fixture;
    protected World world;
    protected TiledMap map;
    protected Body body;
    protected PlayScreen screen;


    public Interactive(PlayScreen screen, Rectangle rect) {
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.screen = screen;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth()/2)/ Catwalk.PPM, (rect.getY() + rect.getHeight()/2)/Catwalk.PPM);

        body = world.createBody(bdef);
        shape.setAsBox((rect.getWidth()/2)/Catwalk.PPM, (rect.getHeight()/2)/Catwalk.PPM);
        fdef.shape = shape;

        fixture = body.createFixture(fdef);
    }

    public abstract void onHeadHit();
    public abstract void onBodyHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(2);
        return layer.getCell((int)(body.getPosition().x * Catwalk.PPM / 16), (int)(body
                .getPosition().y * Catwalk.PPM / 16));
    }
}
