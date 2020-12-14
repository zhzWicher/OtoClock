package com.android.otoclock.db;

public class Music {
    private String musicName;

    private String singerName;

    public Music(String musicName,String singerName) {
        this.musicName = musicName;
        this.singerName = singerName;
    }

    public String getMusicName() {
        return musicName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }
}
