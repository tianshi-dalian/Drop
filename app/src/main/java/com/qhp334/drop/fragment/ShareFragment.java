package com.qhp334.drop.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qhp334.drop.R;
import com.qhp334.drop.adapter.ShareListAdapter;
import com.qhp334.drop.base.BaseFragment;
import com.qhp334.drop.bean.Share;
import com.qhp334.drop.service.Const;
import com.qhp334.drop.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private ShareListAdapter adapter;

    private Retrofit retrofit ;
//    List<Share> list;

    public ShareFragment() {
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

        recyclerView = view.findViewById(R.id.share_recyclerView);
//        list=new ArrayList<>();

        retrofit();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Share>> call = retrofitService.getAllShare();
        call.enqueue(new Callback<List<Share>>() {
            @Override
            public void onResponse(Call<List<Share>> call, Response<List<Share>> response) {

                if (getActivity() != null) {

                    adapter = new ShareListAdapter(getActivity(),response.body());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
//
//                else {
//                    Toast.makeText(getActivity(), "error1", Toast.LENGTH_SHORT).show();
//                }
            }

            @Override
            public void onFailure(Call<List<Share>> call, Throwable t) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fshare, container, false);
    }

}
