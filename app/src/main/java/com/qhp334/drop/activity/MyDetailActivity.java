package com.qhp334.drop.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qhp334.drop.R;
import com.qhp334.drop.bean.UserMod;
import com.qhp334.drop.bean.UserUpdate;
import com.qhp334.drop.service.Const;
import com.qhp334.drop.service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.qhp334.drop.base.BaseActivity.uId;

public class MyDetailActivity extends Activity {

    private EditText username;
    private EditText password1;
    private EditText password2;
    private EditText telephone;
    private ImageView pic;
    private Button modButton;
    private Button cancelButton;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);
        context = MyDetailActivity.this;
        username = findViewById(R.id.new_name);
        password1 = findViewById(R.id.new_password);
        telephone = findViewById(R.id.new_phone);
        modButton = findViewById(R.id.button_mod);
        cancelButton = findViewById(R.id.back_mod);
        getUserById(uId);

        modButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String pwd = password1.getText().toString();
                String phone = telephone.getText().toString();
                updateUserById(uId, name, pwd, phone);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void getUserById(String uid) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<UserMod> call = retrofitService.getuser(uid);
        call.enqueue(new Callback<UserMod>() {
            @Override
            public void onResponse(Call<UserMod> call, Response<UserMod> response) {
                UserMod userMod = response.body();
                if (userMod != null) {
                    username.setText(response.body().getUname());
                    telephone.setText(response.body().getTelephone());
                    password1.setText(response.body().getPassword());
                }
            }

            @Override
            public void onFailure(Call<UserMod> call, Throwable t) {

            }
        });
    }

    private void updateUserById(String userId, String uname, String password, String telephone) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<UserUpdate> call = retrofitService.update_user(userId, uname, password, telephone);
        call.enqueue(new Callback<UserUpdate>() {
            @Override
            public void onResponse(Call<UserUpdate> call, Response<UserUpdate> response) {
                Toast.makeText(MyDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserUpdate> call, Throwable t) {

            }
        });

    }
}
