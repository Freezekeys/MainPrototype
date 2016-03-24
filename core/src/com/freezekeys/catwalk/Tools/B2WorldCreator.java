package com.freezekeys.catwalk.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Entities.Powerup;
import com.freezekeys.catwalk.Entities.Wall;
import com.freezekeys.catwalk.Entities.Water;

/**
 * Created by xrans on 3/16/2016.
 */
public class B2WorldCreator {

    public B2WorldCreator(World world, TiledMap map) {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //mapboundary object, collision boxes
        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ Catwalk.PPM, (rect.getY() + rect.getHeight()/2)/Catwalk.PPM);

            body = world.createBody(bdef);
            shape.setAsBox((rect.getWidth()/2)/Catwalk.PPM, (rect.getHeight()/2)/Catwalk.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //powerups object, collision boxes
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Powerup(world, map, rect);
            Gdx.app.log("Creator"," Creating power-up");
        }

        //walls object, collision boxes
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Wall(world,map,rect);
            Gdx.app.log("Creator"," Creating walls");
        }

        //water object, collision boxes
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Water(world,map,rect);
            Gdx.app.log("Creator", " Creating water surface");
        }
    }
}
