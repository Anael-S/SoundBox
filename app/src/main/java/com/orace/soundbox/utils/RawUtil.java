package com.orace.soundbox.utils;

import com.orace.soundbox.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * This class is used to recover all the name of the file stocked in the raw folder
 */
public class RawUtil {

    /**
     * Return all the filenames of the files in the raw folder
     * @return
     */
    public static ArrayList<String> getAllFilenameFromRawFolder(){
        ArrayList<String> filenames = new ArrayList<String>();
        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){
            filenames.add(fields[count].getName());
        }
        return filenames;
    }
}
