package com.freezekeys.catwalk.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.freezekeys.catwalk.Catwalk;
import com.freezekeys.catwalk.Scenes.Hud;
import com.freezekeys.catwalk.Screens.PlayScreen;
import com.freezekeys.catwalk.Tools.Settings;


/**
 * Created by xrans on 3/16/2016.
 */
public class Powerup extends Interactive{

    public Powerup(PlayScreen screen, Rectangle rect) {
        super(screen, rect);
        fixture.setUserData(this);
        setCategoryFilter(Catwalk.POWERUP_BIT);
    }

    @Override
    public void onHeadHit() {

    }

    @Override
    public void onBodyHit() {
        Hud.changeSpeed(0.07f);
        setCategoryFilter(Catwalk.DESTROYED_BIT);
        getCell().setTile(null);
        if(Settings.sfxEnabled) Catwalk.manager.get("audio/sound/catwalk_pickup.wav", Sound
                .class).play();
    }
}
