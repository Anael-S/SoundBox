package com.orace.soundbox.model;

/**
 * Model class representing our sound.
 * Filename is supposed to be the name of the .mp3 file from the raw folder
 * Title is the label we're going to display on the button wich play the sound
 */
public class Sound {
    String filename;
    String title;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Sound() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sound)) {
            return false;
        }

        Sound sound = (Sound) o;

        if (getFilename() != null ? !getFilename().equals(sound.getFilename()) : sound.getFilename() != null) {
            return false;
        }
        return !(getTitle() != null ? !getTitle().equals(sound.getTitle()) : sound.getTitle() != null);

    }

    @Override
    public int hashCode() {
        int result = getFilename() != null ? getFilename().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        return result;
    }
}
