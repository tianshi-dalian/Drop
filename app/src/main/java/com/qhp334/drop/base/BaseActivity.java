package com.qhp334.drop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Admin on 2018/3/29.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static  String uId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static  String getuId(){
        return uId;
    }
    public static void setuId(String uid){
        uId = uid;
    }

}
