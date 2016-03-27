package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.freezekeys.catwalk.Scenes.Hud;
import com.freezekeys.catwalk.Screens.PlayScreen;

/**
 * Created by xrans on 3/16/2016.
 */
public class Water extends Interactive{
    private PlayScreen s;
    public Water(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        s = screen;
        fixture.setUserData(this);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onBodyHit() {
        Gdx.app.log("Water", "Collision with water, you drowned - ha ha ha.");
        Hud.changeSpeed(-100f);
        s.pause();
    }
}
