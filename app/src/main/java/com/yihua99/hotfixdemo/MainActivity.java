package com.yihua99.hotfixdemo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.beta.Beta;
import com.yihua99.hotfixdemo.app.Utils;
import com.yihua99.jnitest.JniUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tvHello;
    private TextView tvChannel;
    private TextView tvJniTest;
    private TextView tvVersion;

    private Button btnLogin;
    private Button btnSetJniText;

    private TextView tvAppInfo;

    private static boolean isPatch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        tvHello = (TextView) findViewById(R.id.tv_hello);
        tvChannel = (TextView) findViewById(R.id.tv_channel);
        tvJniTest = (TextView) findViewById(R.id.tv_jnitest);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        btnSetJniText = (Button) findViewById(R.id.btn_set_jnitest);
        btnSetJniText.setOnClickListener(this);

        tvChannel.setText("channel:" + Utils.getLC());

        tvVersion = (TextView) findViewById(R.id.tv_version);
        tvVersion.setText(getVersion());

        tvAppInfo = (TextView) findViewById(R.id.tv_app_info);

        if (isPatch) {
            tvAppInfo.setText("这是Patch包");
        } else {
            tvAppInfo.setText("This is base Package!");
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                clickLogin();
                break;
            case R.id.btn_set_jnitest: {
                JniUtils jniUtils = new JniUtils();
                String text = jniUtils.getStringFormC();
                tvJniTest.setText("jniText:" + text);
                Toast.makeText(this,text,Toast.LENGTH_LONG).show();
                break;
            }
            default:
                break;
        }
    }

    private void clickLogin() {
        ClassLoader classLoader = getClassLoader();
        while (classLoader != null) {
            Log.e("MainActivity",classLoader + "");
            classLoader = classLoader.getParent();
        }

        String userName = getUserName();
        // userName != null &&
        if (userName != null && userName.equals("123")) {
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_LONG).show();
        }
    }

    private String getUserName() {
        return null;
    }


    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return  version;
        } catch (Exception e) {
            e.printStackTrace();
            return "0.0";
        }
    }



}
