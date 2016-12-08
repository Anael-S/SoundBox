package com.orace.soundbox.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.orace.soundbox.R;
import com.orace.soundbox.model.Sound;

import java.util.ArrayList;

/**
 * Adapter used for the gridview wich display the sound buttons
 * You just need to set the @soundList (containing all the sounds, wich should also be present in the "raw" folder) in the constructor
 */
public class GridSoundAdapter extends BaseAdapter {

    static class ViewHolder {
        Button soundButton;
    }

    private Context context;
    //Classic soundlist, if valued, the button will display the name of the file (located in raw folder)
    private ArrayList<String> soundList;
    //Specified soundlist, if valued, the button will display the "title" attribute from the Sound class
    private ArrayList<Sound> soundListWithTitle;

    /**
     * Constructor using fields.
     * You can either pass a list of String or a list of Sound
     * @param context
     * @param soundList
     */
    public GridSoundAdapter(Context context, ArrayList<?> soundList) {
        this.context = context;
        if (soundList != null && !soundList.isEmpty()) {
            if (soundList.get(0) instanceof String) {
                this.soundList = (ArrayList<String>) soundList;
            } else if (soundList.get(0) instanceof Sound) {
                this.soundListWithTitle = (ArrayList<Sound>) soundList;
            }
        }
    }

    /**
     * This method is automatically called when our gridview is instanciate with this adapter.
     * It gets called for every element in our soundList (via getCount() function)
     * @param position
     * @param convertView
     * @param parent
     * @return the view created
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View adapterView = convertView;
        ViewHolder holder = null;

        if (adapterView == null) {
            //We get our view
            adapterView = inflater.inflate(R.layout.adapter_sound, null);
            holder = new ViewHolder();
            //We recover the sound button
            holder.soundButton = (Button) adapterView.findViewById(R.id.btnSound);
            //We get the current sound from the soundList
            String lCurrentSound = "";
            if (soundList != null) {
                lCurrentSound = soundList.get(position);
                //We display the label of our sound on the button
                holder.soundButton.setText(lCurrentSound);
            } else if (soundListWithTitle != null){
                lCurrentSound = soundListWithTitle.get(position).getFilename();
                //We display the label of our sound on the button
                holder.soundButton.setText(soundListWithTitle.get(position).getTitle());
            }
            try {
                int id = context.getResources().getIdentifier(lCurrentSound, "raw", context.getPackageName());
                final MediaPlayer lMediaPlayer = MediaPlayer.create(context, id);
                //We affect the correct sound to the button
                holder.soundButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //We play the sound
                        lMediaPlayer.start();
                    }
                });
            } catch (Resources.NotFoundException e) {
                //If the sound isn't found, we disable the button
                holder.soundButton.setEnabled(false);
                holder.soundButton.setBackgroundColor(Color.GRAY);
                holder.soundButton.setText("/");
            }

            adapterView.setTag(holder);
        } else {
            adapterView = (View) convertView;
        }

        return adapterView;
    }

    /**
     * This method is used to count all the items inside the gridview
     * @return
     */
    @Override
    public int getCount() {
        int lSize = 0;
        if (soundList != null && !soundList.isEmpty()){
            lSize = soundList.size();
        } else if (soundListWithTitle != null && !soundListWithTitle.isEmpty()){
            lSize = soundListWithTitle.size();
        }
        return lSize;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
