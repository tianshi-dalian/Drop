package com.qhp334.drop.base;

import android.support.v4.app.Fragment;

import com.qhp334.drop.service.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 2018/3/29.
 */

public abstract class BaseFragment extends Fragment {

//    public Retrofit retrofit ;
    public void setId(String userId){
        BaseActivity.setuId(userId);
    }

    public String getUserId(){
        return BaseActivity.getuId();
    }


}
