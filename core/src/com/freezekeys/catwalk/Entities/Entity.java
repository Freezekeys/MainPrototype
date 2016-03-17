package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by xrans on 3/9/2016.
 */
public class Entity {

    private Vector2 pos;

    public Entity(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getPos() {
        return pos;
    }

    public void move(Vector2 v){
        pos.add(v);
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void render(){

    }

    public void update(){

    }
}
