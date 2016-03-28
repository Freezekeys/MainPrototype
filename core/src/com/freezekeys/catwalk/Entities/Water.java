package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.freezekeys.catwalk.Misc;
import com.freezekeys.catwalk.Scenes.Hud;
import com.freezekeys.catwalk.Screens.PlayScreen;
import com.freezekeys.catwalk.Screens.SelectScreen;

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
        s.gameOver(Misc.STATUS_DROWNED, !Misc.WIN);
    }
}
