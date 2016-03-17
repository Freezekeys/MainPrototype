package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.freezekeys.catwalk.Catwalk;


/**
 * Created by xrans on 3/16/2016.
 */
public class Powerup extends Interactive{

    public Powerup(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);

    }
}
