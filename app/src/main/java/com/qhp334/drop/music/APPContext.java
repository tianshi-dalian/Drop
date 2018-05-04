package com.qhp334.drop.music;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;


import com.qhp334.drop.bean.Song;
import com.qhp334.drop.service.MusicServer;

import java.util.List;

/**
 * Created by user on 2017/5/1.
 */

public class APPContext extends Application {
    public static APPContext instance;
    public static MediaPlayer player;
    private List<Song> songs;
    public Song song;


    public static APPContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        songs = MusicUtils.getMusicData(this);
        player = new MediaPlayer();
        instance = this;
        startService(new Intent(this, MusicServer.class));
    }
    public Song Song(Context context){
        songs = MusicUtils.getMusicData(this);
        player = new MediaPlayer();
        instance = this;
        startService(new Intent(this, MusicServer.class));
        return songs.get(0);
    }
    public List<Song> getSongs() {
        return songs;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Song getSong() {
        return song;
    }

    public static MediaPlayer getPlager() {
        return player;
    }
}
