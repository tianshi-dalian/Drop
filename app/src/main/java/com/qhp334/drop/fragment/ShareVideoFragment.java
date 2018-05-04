package com.qhp334.drop.fragment;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.qhp334.drop.R;

import okhttp3.internal.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareVideoFragment extends Fragment {
    private VideoView videoView_share;

    public ShareVideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        videoView_share = view.findViewById(R.id.video_imageView);

        String videoUrl1 = Environment.getExternalStorageDirectory().getPath()+"/1.mp4";
//        videoView_share.setMediaController(new MediaController(videoView_share.getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_video, container, false);



    }

}
