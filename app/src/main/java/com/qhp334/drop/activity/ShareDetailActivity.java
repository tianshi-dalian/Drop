package com.qhp334.drop.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qhp334.drop.R;
import com.qhp334.drop.base.BaseActivity;
import com.qhp334.drop.bean.Share;
import com.qhp334.drop.service.Const;
import com.qhp334.drop.service.RetrofitService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShareDetailActivity extends BaseActivity {

    private Button backButton;

    private ImageView imageView;
    private ImageView uPicView;

    private TextView uNameView;
    private TextView createTimeView;
    private TextView ShareView;

    private int Sid;
    private String STitle;
    private String SCreateTime;
    private String SPic;

    private int Uid;
    private String UName;
    private String UPic;

    private Context context;

    private Retrofit retrofit;
    Share sharebean;

    public void retrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_detail);
        context = ShareDetailActivity.this;
        sharebean=getIntent().getParcelableExtra("sid");
        Sid = sharebean.getSid();
        Uid = sharebean.getUid();
        //Toast.makeText(context, Sid, Toast.LENGTH_SHORT).show();
        UName = sharebean.getUname();
        UPic = sharebean.getPic();

        STitle = sharebean.getSdetail();
        SPic = sharebean.getSmedia();
        SCreateTime = sharebean.getCreatetime();

        imageView = (ImageView) findViewById(R.id.imageView_d);
        uPicView = (ImageView) findViewById(R.id.uPic_d);
        uNameView = (TextView) findViewById(R.id.uName_d);
        createTimeView = (TextView) findViewById(R.id.createTime_d);
        ShareView = (TextView) findViewById(R.id.title_d);
        backButton = (Button) findViewById(R.id.back_d);
        ShareView.setText(STitle);
        createTimeView.setText("" + SCreateTime);
        uNameView.setText("" + UName);
        if (UPic.equals("")){
            Picasso.with(context).load(R.mipmap.ic_launcher_round).into(uPicView);
        }else {
            Picasso.with(context).load(Const.User_URL+UPic).into(uPicView);
        }
        if (SPic.equals("")){
            Picasso.with(context).load(R.drawable.background).into(imageView);
        }else {
            Picasso.with(context).load(Const.Share_Url+SPic).into(imageView);
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}

