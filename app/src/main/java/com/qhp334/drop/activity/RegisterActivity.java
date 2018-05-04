package com.qhp334.drop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qhp334.drop.R;
import com.qhp334.drop.bean.Register;
import com.qhp334.drop.service.Const;
import com.qhp334.drop.service.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends Activity {

    private EditText telephoneView;
    private EditText uNameView;
    private EditText passwordView;
    private EditText password2View;

    private Button backButton;
    private Button regButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        telephoneView = findViewById(R.id.telephone);
        uNameView = findViewById(R.id.uName);
        passwordView = findViewById(R.id.password);
        password2View = findViewById(R.id.password2);

        backButton = findViewById(R.id.cancel_register);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        regButton = findViewById(R.id.register);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Const.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                RetrofitService retrofitService =
                        retrofit.create(RetrofitService.class);
                final String uname = uNameView.getText().toString();
                String password= passwordView.getText().toString();
                String password2= password2View.getText().toString();
                final String telephone = telephoneView.getText().toString();

                if (!password.equals("")&& password.equals(password2)){
                    Call<Register> registerCall = retrofitService.register(telephone,uname,password);
                    registerCall.enqueue(new Callback<Register>() {
                        @Override
                        public void onResponse(Call<Register> call, Response<Register> response) {
                            Register register = response.body();
                            if (register.getSuccess().equals("0")){
                                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                Intent data = new Intent();
                                data.putExtra("telephone",telephone);
                                setResult(RESULT_OK,data);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Register> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this, "请输入正确注册信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
