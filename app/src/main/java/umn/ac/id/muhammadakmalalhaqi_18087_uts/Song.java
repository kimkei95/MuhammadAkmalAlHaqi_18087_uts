package umn.ac.id.muhammadakmalalhaqi_18087_uts;

import java.io.Serializable;

import umn.ac.id.muhammadakmalalhaqi_18087_uts.MainActivity;

public class Song implements Serializable {
    private int songID;
    private String songTitle;
    public Song(int songID, String songTitle) {
        this.songID=songID;
        this.songTitle=songTitle;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
}
