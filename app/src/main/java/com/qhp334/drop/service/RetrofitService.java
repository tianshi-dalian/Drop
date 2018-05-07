package com.qhp334.drop.service;

import com.qhp334.drop.bean.Login;
import com.qhp334.drop.bean.Register;
import com.qhp334.drop.bean.Share;
import com.qhp334.drop.bean.TextBean;
import com.qhp334.drop.bean.UserMod;
import com.qhp334.drop.bean.UserUpdate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Admin on 2018/3/29.
 */

public interface RetrofitService {

    @POST("User/doreg")
    Call<Register> register(@Query("telephone") String telephone,
                            @Query("uname") String uname,
                            @Query("password") String password);

    @POST("User/dologin")
    Call<Login> dologin(@Query("telephone") String telephone,
                        @Query("password") String password);

    @POST("Share/getShare")
    Call<List<Share>> getAllShare();

    @POST("Share/Sdetail")
    Call<Share> getdetailShare(@Query("Share_sid") int sid,
                               @Query("Share_detail") String sdetail,
                               @Query("Share_createTime") String scretaetime,
                               @Query("Share_smedia") String smedia,
                               @Query("Share_uid") int uid,
                               @Query("Share_uname") String unmae,
                               @Query("Share_upic") String pic
    );

    @POST("Share/searchShare")
    Call<List<Share>> searchShare(@Query("sdetail") String sdetail);

    @POST("ShareText/getAllText")
    Call<List<TextBean>> getAllText();

    /*获取个人信息*/
    @POST("User/show_user")
    Call<UserMod> getuser(@Query("uid") String uid);
//修改个人信息
    @POST("User/update_user")
    Call<UserUpdate> update_user(@Query("uid") String uid,
                                 @Query("uname") String uname,
                                 @Query("password") String password,
                                 @Query("telephone") String telephone

    );
}
