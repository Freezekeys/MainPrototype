package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Screens.PlayScreen;

/**
 * Created by xrans on 3/24/2016.
 */
public abstract class Enemy extends Sprite {
    protected World world;
    protected Fixture fixture;
    public Body body;

    public Enemy(PlayScreen screen, Rectangle rect){
        this.world = screen.getWorld();
        defineEnemy(rect.getX(),rect.getY());

        BodyDef bdef = new BodyDef();

        bdef.position.set(rect.getX() / Catwalk.PPM, rect.getY() / Catwalk.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Catwalk.PPM);

        fdef.filter.categoryBits = Catwalk.ENEMY_BIT;
        fdef.filter.maskBits = Catwalk.GROUND_BIT | Catwalk.POWERUP_BIT
                | Catwalk.WALL_BIT | Catwalk.ENEMY_BIT |
                Catwalk.OBJECT_BIT | Catwalk.CAT_BIT;

        fdef.shape = shape;
        body.createFixture(fdef);
    }

    protected abstract void defineEnemy(float x, float y);

    public abstract void update(float dt);

    public abstract void onBodyHit();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

}
