package com.orace.soundbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.orace.soundbox.adapter.GridSoundAdapter;
import com.orace.soundbox.model.Sound;
import com.orace.soundbox.utils.JsonUtil;
import com.orace.soundbox.utils.RawUtil;

import java.util.ArrayList;

/**
 * This activity contains the gridview (wich itself contains the sound button)
 * We load all the sound name from our json file then
 * give it to the adapter who is going to display a button for each sound
 */
public class MainActivity extends AppCompatActivity {

    GridView gridSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> soundList;

        //We recover our gridView
        gridSound = (GridView) findViewById(R.id.gridSound);

        //You can set the soundList as you want :
        //Either we get all the file from raw (if you want to display 1 button for each sound on the raw folder)
        soundList = RawUtil.getAllFilenameFromRawFolder();

        //Or you can create the list by yourself and add the sound manually
        //with this method you can add the same sound multiple times
        soundList = getListOfSounds();

        //Or you can load some custom sound from a Json file, with that method a custom label will be displayed on the sound button
        ArrayList<Sound> soundsWithTitle = JsonUtil.getSoundsFromJson(this);

        //Then pass your sound list to the adapter
        //gridSound.setAdapter(new GridSoundAdapter(this,soundList)); //Works too !
        gridSound.setAdapter(new GridSoundAdapter(this, soundsWithTitle));
    }

    private ArrayList<String> getListOfSounds() {
        ArrayList<String> lSoundList = new ArrayList<>();
        lSoundList.add("silence2");
        lSoundList.add("notFound"); // Doesn't exist : Thanks to the catch in GridSoundAdapter, the button become disabled
        lSoundList.add("silence1");
        lSoundList.add("silence");
        lSoundList.add("silence");
        lSoundList.add("silence");
        lSoundList.add("silence2");
        lSoundList.add("silence");
        lSoundList.add("silence2");
        lSoundList.add("silence");
        lSoundList.add("silence1");
        lSoundList.add("silence1");
        return lSoundList;
    }
}
