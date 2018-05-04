package com.qhp334.drop.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qhp334.drop.R;
import com.qhp334.drop.adapter.ShareListAdapter;
import com.qhp334.drop.bean.Share;
import com.qhp334.drop.service.Const;
import com.qhp334.drop.service.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchShareActivity extends Activity {

    private Button searchButton;
    private Button backButton;
    private EditText searchText;
    private RecyclerView recyclerView;
    private ShareListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_share);

        recyclerView = findViewById(R.id.search_recyclerView);
        searchText = findViewById(R.id.editText_search);
        searchButton = findViewById(R.id.button_shareSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareTitle = searchText.getText().toString();
                searchShare(shareTitle);
            }
        });
        backButton = findViewById(R.id.button_cancel);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    private void searchShare(final String shareTitle){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<List<Share>> call = retrofitService.searchShare(shareTitle);
        call.enqueue(new Callback<List<Share>>() {
            @Override
            public void onResponse(Call<List<Share>> call, Response<List<Share>> response) {
                adapter = new ShareListAdapter(SearchShareActivity.this,response.body());
                LinearLayoutManager layoutManager = new LinearLayoutManager(SearchShareActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
            }

            @Override
            public void onFailure(Call<List<Share>> call, Throwable t) {
                Toast.makeText(SearchShareActivity.this, "GG", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
