package com.freezekeys.catwalk.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;



/**
 * Created by Lucas on 25.03.2016.
 */
public class Settings {
    public static boolean sfxEnabled = true, musicEnabled = true, muted = false;
    public static int hs_one, hs_two, hs_three, hs_four;
    public static Preferences saveData = Gdx.app.getPreferences("Catwalk_saveData");



    public static void savePrefs(){
        saveData.putBoolean("sfx", sfxEnabled);
        saveData.putBoolean("music", musicEnabled);
        saveData.putBoolean("mute", muted);
        saveData.putInteger("HS1", hs_one);
        saveData.putInteger("HS2", hs_two);
        saveData.putInteger("HS3", hs_three);
        saveData.putInteger("HS4", hs_four);
        saveData.flush();
    }

    public static void loadPrefs(){
        sfxEnabled = saveData.getBoolean("sfx");
        musicEnabled = saveData.getBoolean("music");
        muted = saveData.getBoolean("mute");
        hs_one = saveData.getInteger("HS1");
        hs_two = saveData.getInteger("HS2");
        hs_three = saveData.getInteger("HS3");
        hs_four = saveData.getInteger("HS4");
    }

    public static void wipeCache(){
        sfxEnabled = true;
        musicEnabled = true;
        muted = false;
        hs_four = 0;
        hs_one = 0;
        hs_three = 0;
        hs_two = 0;
        savePrefs();
    }



}
