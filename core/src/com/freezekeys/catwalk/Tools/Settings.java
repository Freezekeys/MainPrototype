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
        savePreference("sfx", sfxEnabled);
        savePreference("music", musicEnabled);
        savePreference("mute", muted);
        savePreference("HS1", Settings.hs_one);
        savePreference("HS2", Settings.hs_two);
        savePreference("HS3", Settings.hs_three);
        savePreference("HS4", Settings.hs_four);
        saveData.flush();
    }

    public static void savePreference(String s, boolean value){
        saveData.putBoolean(s, value);
    }

    public static void savePreference(String s, int value){
        saveData.putInteger(s, value);
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
