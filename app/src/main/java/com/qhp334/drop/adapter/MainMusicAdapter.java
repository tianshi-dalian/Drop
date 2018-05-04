package com.qhp334.drop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.qhp334.drop.music.APPContext;
import com.qhp334.drop.music.CollectDBHelper;
import com.qhp334.drop.music.MusicUtils;
import com.qhp334.drop.R;
import com.qhp334.drop.bean.Song;

import java.util.List;


public class MainMusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnMusicItemListener listener;
    private List<Song> songs;

    public MainMusicAdapter(Context context, List<Song> songs, OnMusicItemListener listener) {
        this.context = context;
        this.songs = songs;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_music, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Song song = songs.get(position);
        Song curSong = APPContext.getInstance().getSong();
        if (curSong != null && curSong.path.equals(song.path)) {
            ((MyViewHolder) holder).numTv.setBackgroundResource(R.drawable.icon_playing);
            ((MyViewHolder) holder).numTv.setText("");
        } else {
            ((MyViewHolder) holder).numTv.setBackgroundResource(0);
            ((MyViewHolder) holder).numTv.setText(String.valueOf(position+1));
        }
        ((MyViewHolder) holder).collectCb.setChecked(CollectDBHelper.getInstance(context).isExist(song));
        ((MyViewHolder) holder).nameTv.setText(song.song);
        ((MyViewHolder) holder).authorTv.setText(song.singer);
        ((MyViewHolder) holder).timeTv.setText(MusicUtils.formatTime(song.duration));
        ((MyViewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listener.onMusicClick(position, song);
            }
        });

        //收藏
        ((MyViewHolder) holder).collectCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onCollectClick(position, song, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView numTv;
        private TextView nameTv;
        private TextView authorTv;
        private TextView timeTv;
        private CheckBox collectCb;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            numTv =  itemView.findViewById(R.id.item_music_num_tv);
            nameTv = itemView.findViewById(R.id.item_music_center_name);
            timeTv = itemView.findViewById(R.id.item_music_time);
            authorTv = itemView.findViewById(R.id.item_music_center_author);
            collectCb = itemView.findViewById(R.id.item_music_collect);
        }

    }

    public interface OnMusicItemListener {
        void onMusicClick(int position, Song song);

        void onCollectClick(int position, Song song, boolean isCheck);
    }
}
