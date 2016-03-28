package com.freezekeys.catwalk.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Entities.Dog;
import com.freezekeys.catwalk.Entities.EndObject;
import com.freezekeys.catwalk.Entities.Powerup;
import com.freezekeys.catwalk.Entities.Wall;
import com.freezekeys.catwalk.Entities.Water;
import com.freezekeys.catwalk.Screens.PlayScreen;

/**
 * Created by xrans on 3/16/2016.
 */
public class B2WorldCreator {

    private Vector2 levelStart;
    private PlayScreen screen;

    public B2WorldCreator(PlayScreen screen) {
        this.screen = screen;
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
        levelStart = new Vector2(0,0);

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
            fdef.filter.categoryBits = Catwalk.GROUND_BIT;
            body.createFixture(fdef);

        }

        //powerups object, collision boxes
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Powerup(screen, rect);
        }

        //walls object, collision boxes
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Wall(screen,rect);
        }

        //water object, collision boxes
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Water(screen,rect);
        }
        //end object, collision boxes
        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new EndObject(screen,rect);
        }


        //dog object, collision boxes
        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject
                .class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            screen.getEnemies().add(new Dog(screen, rect));
        }

        /* Get level start */
        MapObject object = map.getLayers().get(7).getObjects().getByType(RectangleMapObject
                .class).get(0);
        Rectangle rect = ((RectangleMapObject) object).getRectangle();
        rect.getCenter(levelStart);
    }

    public Vector2 getLevelStart(){
        return levelStart;
    }
}
