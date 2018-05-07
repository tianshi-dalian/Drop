package com.qhp334.drop.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qhp334.drop.MainActivity;
import com.qhp334.drop.R;
import com.qhp334.drop.activity.RegisterActivity;
import com.qhp334.drop.base.BaseFragment;
import com.qhp334.drop.bean.Login;
import com.qhp334.drop.service.Const;
import com.qhp334.drop.service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {

    private Button regButton;
    private Button loginButton;
    private Button noLoginButton;

    private EditText telephoneView;
    private EditText passwordView;

    private MainFragment mainFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    SharedPreferences sp;
    private final static  String s= "UserInfo";
    SharedPreferences.Editor editor;

    public LoginFragment() {
        // Required empty public constructor
    }

    private void saveUser(int u_id){
        sp = getActivity().getSharedPreferences(s, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.putString("user_telephone",telephoneView.getText().toString());
        editor.putInt("user_id",u_id);
        editor.putString("user_password",passwordView.getText().toString());
        editor.commit();
        //  System.out.println("保存的用户的id为："+u_id);
        Log.v("userssssssssssss",String.valueOf(u_id));
        Log.v("userssssssssssss",telephoneView.getText().toString());
        Log.v("userssssssssssss",passwordView.getText().toString());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        telephoneView = view.findViewById(R.id.telephone_login);
        passwordView = view.findViewById(R.id.password_login);
        loginButton = view.findViewById(R.id.login_button);
        regButton = view.findViewById(R.id.register_button);
//        noLoginButton = view.findViewById(R.id.noLogin_login);

        fragmentManager = getChildFragmentManager();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Const.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitService retrofitService =
                        retrofit.create(RetrofitService.class);


                String username = telephoneView.getText().toString();
                String password = passwordView.getText().toString();
                Call<Login> loginCall = retrofitService.dologin(username,password);

                loginCall.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Login login= response.body();
                        if (!login.getUid().equals("0")){
                            Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                            MainActivity activity = (MainActivity) getActivity();
                            if (activity !=null)
                                activity.replaceLoginFragment();
                                String userId= login.getUid();
                                activity.saveUid(userId);
                                activity.setuId(userId);
                        }else {
                            Toast.makeText(getActivity(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {

                    }
                });
            }
        });


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);
//                Intent intent = new Intent(getActivity(),RegisterActivity.class);
//                startActivityForResult(intent,1);
            }
        });




    }
    //添加获取返回值的回调方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode ==1){
            switch (requestCode){
                case RESULT_OK:
                    String telephone = data.getStringExtra("telephone");
                    telephoneView.setText(telephone);
                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
