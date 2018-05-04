package com.qhp334.drop.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;


import com.qhp334.drop.music.APPContext;
import com.qhp334.drop.music.CollectDBHelper;
import com.qhp334.drop.music.Constants;
import com.qhp334.drop.music.MusicUtils;
import com.qhp334.drop.bean.Song;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 2017/5/1.
 */

public class MusicServer extends Service implements MediaPlayer.OnCompletionListener {
    private MediaPlayer player;
    private PlayerReceiver receiver;
    private List<Song> songs;
    private Context mcontext;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;
        initData();
    }


    private void initData() {
        player = APPContext.getPlager();
        songs = APPContext.getInstance().getSongs();
        receiver = new PlayerReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.CHANGE_MUSIC_SONG);
        filter.addAction(Constants.CHANGE_ALL_LIST);
        filter.addAction(Constants.CHANGE_COLLECT_LIST);
        registerReceiver(receiver, filter);
        player.setOnCompletionListener(this);
    }

    /**
     * 下一首
     */
    private void nextSong() {
        if (songs.size() > 0) {
            Song song = APPContext.getInstance().getSong();
            int lastPosition = -1;
            if (song != null) {
                for (Song so : songs) {
                    lastPosition++;
                    if (so.path.equals(song.path)) {
                        if (lastPosition != songs.size() - 1) {
                            lastPosition++;
                            Song lastSong = songs.get(lastPosition);
                            sendBroadcastSong(lastSong);
                        }
                        break;
                    }
                }
            }
        }
    }

    private void sendBroadcastSong(Song song) {
        Intent intent = new Intent(Constants.CHANGE_MUSIC_SONG);
        intent.putExtra("song", song);
        sendBroadcast(intent);
        APPContext.getInstance().setSong(song);
        sendBroadcast(new Intent(Constants.CHANGE_MUSIC_UP_MUSIC_DATA));
    }

    private void startNewSong(Song song) {
        try {
            player.reset();
            player.setDataSource(song.path);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        sendBroadcast(new Intent(Constants.CHANGE_PLAYED));
        nextSong();
    }

    private class PlayerReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constants.CHANGE_MUSIC_SONG.equals(intent.getAction())) {
                Song song = (Song) intent.getSerializableExtra("song");
                if (song != null) {
                    startNewSong(song);
                }
            } else if (Constants.CHANGE_ALL_LIST.equals(intent.getAction())) {
                songs.clear();
                songs.addAll(MusicUtils.getMusicData(mcontext));
            } else if (Constants.CHANGE_COLLECT_LIST.equals(intent.getAction())) {
                songs.clear();
                songs.addAll(CollectDBHelper.getInstance(mcontext).getCollectSong(mcontext));
            }
        }
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.reset();
        unregisterReceiver(receiver);
        super.onDestroy();
    }
}
