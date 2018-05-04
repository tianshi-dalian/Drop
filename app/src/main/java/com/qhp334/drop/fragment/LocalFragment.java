package com.qhp334.drop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.qhp334.drop.R;
import com.qhp334.drop.activity.LMusicActivity;
import com.qhp334.drop.activity.OMusicActivity;
import com.qhp334.drop.activity.VideoActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocalFragment extends Fragment {

    private Button btn_l_music;
    private Button btn_video;

    public LocalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        btn_l_music = view.findViewById(R.id.btn_l_music);
        btn_video = view.findViewById(R.id.btn_video);
        btn_l_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LMusicActivity.class);
                startActivity(intent);
            }
        });

        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),VideoActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_local, container, false);
    }

}
