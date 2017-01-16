package com.tencent.hotfixdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import com.tencent.tinker.loader.app.TinkerApplication;
//import com.tencent.tinker.loader.app.DefaultApplicationLike;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tvHello;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvHello = (TextView) findViewById(R.id.tv_hello);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                clickLogin();
                break;
            default:
                break;
        }
    }

    private void clickLogin() {
        String useName = getUserName();
        if (useName != null && useName.equals("123")) {
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_LONG).show();
        }
    }

    private String getUserName() {
        return null;
    }



}
