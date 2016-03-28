package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Scenes.Hud;
import com.freezekeys.catwalk.Screens.PlayScreen;
import com.freezekeys.catwalk.Screens.SelectScreen;

/**
 * Created by xrans on 3/16/2016.
 */
public class Wall extends Interactive{
    public Wall(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        fixture.setUserData(this);
        setCategoryFilter(Catwalk.WALL_BIT);
    }

    @Override
    public void onHeadHit() {
        Hud.changeSpeed(-0.1f);
        Catwalk.manager.get("audio/sound/catwalk_meow.wav", Sound.class).play();
    }

    @Override
    public void onBodyHit() {

    }
}
