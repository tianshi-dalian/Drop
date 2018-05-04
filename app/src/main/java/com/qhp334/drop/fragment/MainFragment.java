package com.qhp334.drop.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.qhp334.drop.R;
import static com.qhp334.drop.R.id.radio_friend;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private RadioGroup radioGroup;

    private LiveFragment liveFragment;
    private FriendFragment friendFragment;
    private MyFragment myFragment;
    private LocalFragment localFragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_live:
                        liveFragment = new LiveFragment();
                        replaceFragment(liveFragment);
                        break;

                    case R.id.radio_mv:
//                        mvFragment = new MvFragment();
                        localFragment = new LocalFragment();
                        replaceFragment(localFragment);
                        break;

                    case R.id.radio_friend:
                        friendFragment = new FriendFragment();
                        replaceFragment(friendFragment);
                        break;


                    case R.id.radio_my:
                        myFragment = new MyFragment();
                        replaceFragment(myFragment);
                        break;
                }
            }
        });

        radioGroup.check(radio_friend);
    }

    private void replaceFragment(Fragment fragment) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentManager = getChildFragmentManager();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

}
