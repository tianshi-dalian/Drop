package com.qhp334.drop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.qhp334.drop.R;
import com.qhp334.drop.activity.AddShareActivity;
import com.qhp334.drop.activity.SearchShareActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendFragment extends Fragment {

    private RadioButton videoRadio;
    private RadioButton shareRadio;
    private RadioButton textRadio;

    private LinearLayout ll_video_color;
    private LinearLayout ll_share_color;
    private LinearLayout ll_text_color;


    private Button issueButton;
    private Button searchButton;

    private ShareFragment shareFragment;
    private TextFragment textFragment;
    private ShareVideoFragment shareVideoFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    public FriendFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        shareRadio = view.findViewById(R.id.radio_share);
        textRadio = view.findViewById(R.id.radio_text);
//        videoRadio = view.findViewById(R.id.radio_video);

        ll_share_color = view.findViewById(R.id.ll_share_color);
        ll_text_color = view.findViewById(R.id.ll_text_color);
//        ll_video_color = view.findViewById(R.id.ll_video_color);
        issueButton = view.findViewById(R.id.issue_friend);
        searchButton = view.findViewById(R.id.search_friend);
        issueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddShareActivity.class);
                startActivity(intent);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchShareActivity.class);
                startActivityForResult(intent,1);
            }
        });


        shareRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    resetColor();
                    shareRadio.setTextColor(getResources().getColor(R.color.Checked));
                    ll_share_color.setBackgroundColor(getResources().getColor(R.color.Checked));
                    shareFragment = new ShareFragment();
                    replaceFragment(shareFragment);
                }
            }
        });

//        videoRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                if (b){
//                    resetColor();
//                    videoRadio.setTextColor(getResources().getColor(R.color.Checked));
//                    ll_video_color.setBackgroundColor(getResources().getColor(R.color.Checked));
//                    shareVideoFragment = new ShareVideoFragment();
//                    replaceFragment(shareVideoFragment);
//                }
//            }
//        });

        textRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    resetColor();
                    textRadio.setTextColor(getResources().getColor(R.color.Checked));
                    ll_text_color.setBackgroundColor(getResources().getColor(R.color.Checked));
                    textFragment = new TextFragment();
                    replaceFragment(textFragment);
                }
            }
        });

        fragmentManager = getChildFragmentManager();
        shareRadio.setChecked(true);

    }
    public void replaceFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_friendHolder, fragment);
        fragmentTransaction.commit();
    }

    public void resetColor() {
        shareRadio.setTextColor(getResources().getColor(R.color.unChecked));
        ll_share_color.setBackgroundColor(getResources().getColor(R.color.unChecked));

//
//        videoRadio.setTextColor(getResources().getColor(R.color.unChecked));
//        ll_video_color.setBackgroundColor(getResources().getColor(R.color.unChecked));

        textRadio.setTextColor(getResources().getColor(R.color.unChecked));
        ll_text_color.setBackgroundColor(getResources().getColor(R.color.unChecked));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend, container, false);
    }

}
