package com.qhp334.drop.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhp334.drop.R;
import com.qhp334.drop.base.BaseActivity;
import com.qhp334.drop.bean.TextBean;
import com.qhp334.drop.service.Const;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TextDetailActivity extends BaseActivity {

    private ImageView upicView;
    private TextView nameView;
    private TextView createTimeView;
    private TextView tDetailView;
    private Button back_textDetail;

    private Context context;
    TextBean textBean;

    private int Tid;
    private int Uid;
    private String Tdetail;
    private String Tcreatetime;
    private String Upic;
    private String Uname;

    public void retrofit(){
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_detail);
        context = TextDetailActivity.this;
        textBean = getIntent().getParcelableExtra("tid");
        Tid = textBean.getTid();
        Uid = textBean.getUid();
        Uname = textBean.getUname();
        Tdetail = textBean.getTdetail();
        Tcreatetime = textBean.getTcreatetime();
        Upic = textBean.getPic();

        upicView = (ImageView) findViewById(R.id.uPic_text);
        nameView = (TextView) findViewById(R.id.uName_text);
        createTimeView = (TextView) findViewById(R.id.createTime_text);
        tDetailView = (TextView) findViewById(R.id.textView_textDetail_text);
        back_textDetail = (Button) findViewById(R.id.back_d);
        tDetailView.setText(Tdetail);
        nameView.setText(Uname);
        createTimeView.setText(Tcreatetime);
        if (Upic.equals("")){
            Picasso.with(context).load(R.mipmap.ic_launcher_round).into(upicView);
        }else {
            Picasso.with(context).load(Const.User_URL+Upic).into(upicView);
        }

        back_textDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
