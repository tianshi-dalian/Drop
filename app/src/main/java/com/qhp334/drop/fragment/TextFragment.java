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
import com.qhp334.drop.adapter.TextListAdapter;
import com.qhp334.drop.base.BaseFragment;
import com.qhp334.drop.bean.TextBean;
import com.qhp334.drop.service.Const;
import com.qhp334.drop.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends BaseFragment {

    private RecyclerView titleRecyclerView ;
    private TextListAdapter adapter2;

    private Retrofit retrofit;

    public TextFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        titleRecyclerView = view.findViewById(R.id.text_recycler);
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<TextBean>> call = retrofitService.getAllText();
        call.enqueue(new Callback<List<TextBean>>() {
            @Override
            public void onResponse(Call<List<TextBean>> call, Response<List<TextBean>> response) {
                if (getActivity() !=null){
                    adapter2 = new TextListAdapter(getActivity(),response.body());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    titleRecyclerView.setLayoutManager(layoutManager);
                    titleRecyclerView.setAdapter(adapter2);
                    titleRecyclerView.setItemAnimator(new DefaultItemAnimator());

                }
                else {
                    Toast.makeText(getActivity(), "没有记录", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TextBean>> call, Throwable t) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_share_text, container, false);
    }

}
