package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by xrans on 3/16/2016.
 */
public class Water extends Interactive{

    public Water(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);
        fixture.setUserData(true);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Water","Collision");
    }
}
