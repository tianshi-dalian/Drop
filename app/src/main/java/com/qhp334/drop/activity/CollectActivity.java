package com.qhp334.drop.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.qhp334.drop.music.APPContext;
import com.qhp334.drop.music.CollectDBHelper;
import com.qhp334.drop.music.Constants;
import com.qhp334.drop.R;
import com.qhp334.drop.adapter.MainMusicAdapter;
import com.qhp334.drop.base.BaseMusicActivity;
import com.qhp334.drop.bean.Song;

import java.util.List;


public class CollectActivity extends BaseMusicActivity implements MainMusicAdapter.OnMusicItemListener {
    private ImageView backIv;
    private RecyclerView recycler;
    private MainMusicAdapter adapter;
    private List<Song> songs;
    private MediaPlayer player;
    private MainReceiver mainReceiver;

    @Override
    public void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_collect);
    }

    @Override
    public void findView() {
        backIv = findViewById(R.id.activity_collect_back);
        recycler =  findViewById(R.id.activity_collect_recycler);
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        player = APPContext.getPlager();
        songs = CollectDBHelper.getInstance(mcontext).getCollectSong(mcontext);
        adapter = new MainMusicAdapter(mcontext, songs, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(mcontext));


        mainReceiver = new MainReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.CHANGE_MUSIC_UP_MUSIC_DATA);
        filter.addAction(Constants.CHANGE_PLAYED);
        registerReceiver(mainReceiver, filter);
    }

    @Override
    public void initListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onMusicClick(int position, Song song) {
        Song oldSong = APPContext.getInstance().getSong();
        sendBroadcast(new Intent(Constants.CHANGE_COLLECT_LIST));
        if (oldSong == null || !oldSong.path.equals(song.path)) {
            sendBroadcastSong(song);
        }
    }

    private void sendBroadcastSong(Song song) {
        sendBroadcast(new Intent(Constants.CHANGE_COLLECT_LIST));
        Intent intent = new Intent(Constants.CHANGE_MUSIC_SONG);
        intent.putExtra("song", song);
        sendBroadcast(intent);
        APPContext.getInstance().setSong(song);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCollectClick(int position, Song song, boolean isCheck) {
        if (!isCheck) {
            CollectDBHelper.getInstance(mcontext).deleteSong(song);
            adapter.notifyItemRemoved(position);
            songs.clear();
            songs.addAll(CollectDBHelper.getInstance(mcontext).getCollectSong(mcontext));
        }

    }

    private class MainReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constants.CHANGE_MUSIC_UP_MUSIC_DATA.equals(intent.getAction())) {
//                stateCb.setChecked(true);
                adapter.notifyDataSetChanged();
            } else if (Constants.CHANGE_PLAYED.equals(intent.getAction())) {
//                stateCb.setChecked(false);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mainReceiver);
        setResult(1, null);
    }
}
