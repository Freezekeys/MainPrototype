package com.freezekeys.catwalk.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.freezekeys.catwalk.Entities.Interactive;

/**
 * Created by xrans on 3/23/2016.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        if(fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;
            if(object.getUserData() instanceof Interactive){
                ((Interactive)  object.getUserData()).onHeadHit();
            }
        }else if(fixA.getUserData() == "body" || fixB.getUserData() == "body"){
            Fixture head = fixA.getUserData() == "body" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;
            if(object.getUserData() instanceof Interactive){
                ((Interactive)  object.getUserData()).onBodyHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
