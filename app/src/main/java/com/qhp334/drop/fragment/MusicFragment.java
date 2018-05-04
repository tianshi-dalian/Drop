package com.qhp334.drop.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qhp334.drop.R;

/**
 * A simple {@link Fragment} subclass.
 */
//public class MusicFragment extends BasePager {
//    public MusicFragment(MusicFragment context) {
//       super(context);
//    }
//
//    @Override
//    public View initview() {
//        return null;
//    }
//
//
//    public void MusicFragment() {
//        // Required empty public constructor
//    }
//
public class MusicFragment extends Fragment {

    public MusicFragment() {
//        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muisc, container, false);
    }

}
