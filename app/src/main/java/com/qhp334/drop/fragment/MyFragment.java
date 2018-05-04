package com.qhp334.drop.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhp334.drop.R;
import com.qhp334.drop.activity.CollectActivity;
import com.qhp334.drop.activity.MyDetailActivity;
import com.qhp334.drop.adapter.ShareListAdapter;
import com.qhp334.drop.bean.UserMod;
import com.qhp334.drop.service.Const;
import com.qhp334.drop.service.RetrofitService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.qhp334.drop.base.BaseActivity.uId;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    private Button collectButton;
    private Button modButton;
    private Button exitButton;

    private ImageView picView;
    private TextView nameView;
    private TextView telephoneView;

    private ShareListAdapter adapter;
    private Retrofit retrofit;
    private Context context;


    public MyFragment() {
        // Required empty public constructor
    }
    public void retrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        modButton = view.findViewById(R.id.mod_user);
        collectButton = view.findViewById(R.id.collect_my);
        exitButton = view.findViewById(R.id.myExit);

        picView = view.findViewById(R.id.uPic_my);
        nameView = view.findViewById(R.id.uName_my);
        telephoneView = view.findViewById(R.id.telephone_my);
        getUser(uId);

        modButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyDetailActivity.class);
                startActivityForResult(intent,1);
            }
        });
        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),CollectActivity.class);
                startActivity(intent);
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDestroy();
            }
        });
    }

    private void getUser(String uid){
        retrofit();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class) ;
        Call<UserMod> call = retrofitService.getuser(uid);
        call.enqueue(new Callback<UserMod>() {
            @Override
            public void onResponse(Call<UserMod> call, Response<UserMod> response) {
                UserMod userMod = response.body();
                if (userMod !=null){
                    nameView.setText("昵称："+response.body().getUname());
                    telephoneView.setText("账号："+response.body().getTelephone());
//                    picView.setVisibility();
                    picView.setImageAlpha(Integer.parseInt(response.body().getPic()));
//                    Picasso.with(context).load(Const.User_URL+picView).into(picView);

//                    picView.setVisibility(View.VISIBLE);
//                    if (picView.equals("")){
//                        Picasso.with(context).load(R.mipmap.ic_launcher_round).into(picView);
//                    }else{
//                        Picasso.with(context).load(Const.User_URL+picView).into(picView);
//                    }

                }
            }

            @Override
            public void onFailure(Call<UserMod> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);


    }

}
