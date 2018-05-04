package com.qhp334.drop;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.qhp334.drop.base.BaseActivity;
import com.qhp334.drop.fragment.LoginFragment;
import com.qhp334.drop.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    private LoginFragment loginFragment;
    private MainFragment mainFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private SharedPreferences sharedPreferences;
    private final String fileName="Drop";
    private final int MODE = MODE_PRIVATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        sharedPreferences = getSharedPreferences(fileName,MODE);
        //判断是否登录，如登录 进入主界面
        //否则显示登录页面
        boolean loginState = isLogined();
        if (loginState){
            showMainFragment();
            uId = readUid();
        }else {
//            showMainFragment();
            showLoginFragment();
        }
    }
    private void showLoginFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        loginFragment = new LoginFragment();
        fragmentTransaction.replace(R.id.fragment_holder, loginFragment);
        fragmentTransaction.commit();
    }
    private void showMainFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        fragmentTransaction.add(R.id.fragment_holder,mainFragment);
        fragmentTransaction.commit();
    }
    public void replaceLoginFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        fragmentTransaction.replace(R.id.fragment_holder, mainFragment);
        fragmentTransaction.commit();
    }
    public boolean isLogined() {
        String uid = readUid();
        uid = readUid();
        return !uid.equals("-1");
    }
    private  String readUid(){
        String uid = sharedPreferences.getString("uid","-1");
        return  uid;
    }
    private String getSaveUid() {
        String uid = sharedPreferences.getString("uid", "-1");
        return uid;
    }
    public void saveUid(String uid){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uid",uid);
        editor.commit();
    }


}

