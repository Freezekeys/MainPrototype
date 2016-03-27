package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Screens.PlayScreen;
import com.freezekeys.catwalk.Screens.SelectScreen;
import com.freezekeys.catwalk.Tools.Settings;

/**
 * Created by xrans on 3/25/2016.
 */
public class EndObject extends Interactive{
    public EndObject(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        fixture.setUserData(this);
        setCategoryFilter(Catwalk.END_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("End:","You have finished the level!");
        screen.levelSuccess();
    }

    @Override
    public void onBodyHit() {

    }
}
