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
//public  class OMusicFragment extends BasePager {
public  class OMusicFragment extends Fragment {


        public OMusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_omusic, container, false);
    }

}
