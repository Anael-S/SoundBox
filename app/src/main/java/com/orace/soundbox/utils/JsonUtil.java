package com.orace.soundbox.utils;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orace.soundbox.model.Sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is used to recovers our list of sound from the json file called "sounds.json"
 */
public class JsonUtil {

    //The name of our json file containing all our sounds
    private static final String FILENAME = "sounds.json";

    /**
     * Recover all the sounds from the local json file
     * In order to change the json file (for example adding and/or deleting properties), you need to also change the Sound.java class
     * or you'll get an IO exception here on the deserialization
     * @param context
     * @return
     */
    public static ArrayList<Sound> getSoundsFromJson(Context context) {

        ArrayList<Sound> lSoundsList = new ArrayList<Sound>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            //We deserialize our json file with jackson lib in order to get an arraylist of our CustomMarker object
            Sound[] lTabSounds = objectMapper.readValue(context.getAssets().open(FILENAME), Sound[].class);
            lSoundsList = new ArrayList<>(Arrays.asList(lTabSounds));
        } catch (IOException e) {
            Log.d("Soundbox", "An error has occured while reading the json file", e);
        }

        return lSoundsList;
    }
}
