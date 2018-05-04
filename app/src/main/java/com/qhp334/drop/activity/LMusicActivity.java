package com.qhp334.drop.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.qhp334.drop.music.APPContext;
import com.qhp334.drop.music.CollectDBHelper;
import com.qhp334.drop.music.Constants;
import com.qhp334.drop.music.MusicUtils;
import com.qhp334.drop.R;
import com.qhp334.drop.adapter.MainMusicAdapter;
import com.qhp334.drop.bean.Song;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class LMusicActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener,
        MainMusicAdapter.OnMusicItemListener {
    private ImageView backMusic;
    private ImageView mainIconIv;
    private TextView musicName;
    private TextView musicAuthor;
    private SeekBar seekBar;
    private TextView timeTv;
    private ImageView lastIv;
    private ImageView nextIv;
    private CheckBox stateCb;
    private RecyclerView recycler;
    private MainMusicAdapter adapter;
    private List<Song> songs;
    private MediaPlayer player;
    private Timer timer;
    private TimerTask timerTask;
    private MainHandler handler;
    private MainReceiver mainReceiver;
    private LinearLayout refreshLl;
    private ImageView refreshIv;
    private boolean isRefresh = false;
    private final int NEXT_SONG = 1;
    private final int UP_TIME = 2;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lmusic);
        findView();
        initView();
        initData();
        initListener();
    }

    public void findView() {
        mainIconIv = (ImageView) findViewById(R.id.activity_main_iv);
        musicName = (TextView) findViewById(R.id.activity_main_music_name);
        musicAuthor = (TextView) findViewById(R.id.activity_main_music_author);
        seekBar = (SeekBar) findViewById(R.id.activity_main_music_seek);
        timeTv = (TextView) findViewById(R.id.activity_main_music_time);
        lastIv = (ImageView) findViewById(R.id.activity_main_last_iv);
        nextIv = (ImageView) findViewById(R.id.activity_main_next_iv);
        stateCb = (CheckBox) findViewById(R.id.activity_main_state_iv);
        recycler = (RecyclerView) findViewById(R.id.activity_main_recycler);
        refreshLl = (LinearLayout) findViewById(R.id.activity_main_refresh);
        refreshIv = (ImageView) findViewById(R.id.activity_main_refresh_iv);
        backMusic = (ImageView) findViewById(R.id.activity_music_back);
    }

    public void initView() {
        mcontext=LMusicActivity.this;
        player = APPContext.getPlager();
        stateCb.setChecked(player.isPlaying());
    }

    private void setSongView(Song song) {
        if (song != null) {
            musicName.setText(song.song);
            musicAuthor.setText(song.singer);
            timeTv.setText(MusicUtils.formatTime(song.duration));
        }
        else
        {
            musicName.setText("不存在");
            musicAuthor.setText("不存在");
        }
    }


    public void initData() {
        mainIconIv.startAnimation(AnimationUtils.loadAnimation(mcontext, R.anim.rotate));
        mainReceiver = new MainReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Constants.CHANGE_MUSIC_UP_MUSIC_DATA);
        filter.addAction(Constants.CHANGE_PLAYED);
        registerReceiver(mainReceiver, filter);
        handler = new MainHandler();
        songs = MusicUtils.getMusicData(mcontext);
        adapter = new MainMusicAdapter(this, songs, this);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(mcontext));
        Song song = songs.get(0);
        setSongView(song);
        if (player.isPlaying()) {
            seekBar.setMax(song.duration);
            seekBar.setProgress(player.getCurrentPosition());
            recordProgress();
        }
    }


    public void initListener() {

        lastIv.setOnClickListener(this);
        nextIv.setOnClickListener(this);
        refreshLl.setOnClickListener(this);
        stateCb.setOnCheckedChangeListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        backMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_last_iv:
                lastSong();
                break;
            case R.id.activity_main_next_iv:
                nextSong();
                break;

            case R.id.activity_main_refresh:
                if (isRefresh) {
                    return;
                }
                isRefresh = true;
                refreshIv.startAnimation(AnimationUtils.loadAnimation(mcontext, R.anim.rotate_500));
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshIv.clearAnimation();
                        sendBroadcast(new Intent(Constants.CHANGE_ALL_LIST));
                        songs.clear();
                        songs.addAll(MusicUtils.getMusicData(mcontext));
                        adapter.notifyDataSetChanged();
                        isRefresh = false;
                        Toast.makeText(mcontext, "刷新成功", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
                break;


        }
    }

    /**
     * 上一首
     */
    private void lastSong() {
        if (songs.size() > 0) {
            Song song = APPContext.getInstance().getSong();
            int lastPosition = -1;
            if (song != null) {
                for (Song so : songs) {
                    lastPosition++;
                    if (so.path.equals(song.path)) {
                        if (lastPosition == 0) {
                            lastPosition = songs.size() - 1;
                        } else {
                            lastPosition--;
                        }
                        break;
                    }
                }
            } else {
                lastPosition++;
            }
            Song lastSong = songs.get(lastPosition);
            sendBroadcastSong(lastSong);
        }
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
                        if (lastPosition == songs.size() - 1) {
                            lastPosition = 0;
                        } else {
                            lastPosition++;
                        }
                        break;
                    }
                }
            } else {
                lastPosition++;
            }
            Song lastSong = songs.get(lastPosition);
            sendBroadcastSong(lastSong);
        }
    }

    private void sendBroadcastSong(Song song) {
        sendBroadcast(new Intent(Constants.CHANGE_ALL_LIST));
        Intent intent = new Intent(Constants.CHANGE_MUSIC_SONG);
        intent.putExtra("song", song);
        sendBroadcast(intent);
        APPContext.getInstance().setSong(song);
        setSongView(song);
        seekBar.setMax(song.duration);
        seekBar.setProgress(0);
        adapter.notifyDataSetChanged();
        stateCb.setChecked(true);
        recordProgress();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            if (songs.size() > 0) {
                Song song = APPContext.getInstance().getSong();
                if (song != null) {
                    player.start();
                    recordProgress();
                } else {
                    song = songs.get(0);
                    sendBroadcastSong(song);
                }
            }
        } else {
            cancelTimer();
            player.pause();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        player.seekTo(seekBar.getProgress());
        handler.sendEmptyMessage(UP_TIME);
    }

    @Override
    public void onMusicClick(int position, Song song) {
        Song oldSong = APPContext.getInstance().getSong();
        if (oldSong == null || !oldSong.path.equals(song.path)) {
            sendBroadcastSong(song);
        }
    }

    @Override
    public void onCollectClick(int position, Song song, boolean isCheck) {
        if (isCheck) {
            CollectDBHelper.getInstance(mcontext).insertSong(song);
        } else {
            CollectDBHelper.getInstance(mcontext).deleteSong(song);
        }
    }

    private void recordProgress() {
        cancelTimer();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(UP_TIME);
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
        if (timerTask != null) {
            timerTask.cancel();
        }
    }

    private class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UP_TIME:
                    Song song = APPContext.getInstance().getSong();
                    seekBar.setProgress(player.getCurrentPosition());
                    timeTv.setText(MusicUtils.formatTime(song.duration - player.getCurrentPosition()));
                    break;
            }

        }
    }

    private class MainReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Constants.CHANGE_MUSIC_UP_MUSIC_DATA.equals(intent.getAction())) {
                Song song = APPContext.getInstance().getSong();
                setSongView(song);
                stateCb.setChecked(true);
                adapter.notifyDataSetChanged();
                recordProgress();
            } else if (Constants.CHANGE_PLAYED.equals(intent.getAction())) {
                stateCb.setChecked(false);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Song song = APPContext.getInstance().getSong();
        setSongView(song);
        if (player.isPlaying()) {
            stateCb.setChecked(true);
            seekBar.setMax(player.getDuration());
            seekBar.setProgress(player.getCurrentPosition());
            recordProgress();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mainReceiver);
        super.onDestroy();
    }
}