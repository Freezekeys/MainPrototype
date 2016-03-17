package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by xrans on 3/16/2016.
 */
public class Wall extends Interactive{

    public Wall(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);
    }
}
