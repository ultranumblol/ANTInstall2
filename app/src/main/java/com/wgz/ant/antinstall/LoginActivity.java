package com.wgz.ant.antinstall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.wgz.ant.antinstall.util.CheckLogin;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;

/**
 * Created by qwerr on 2015/11/17.
 */
public class LoginActivity extends Activity {
    private EditText login_name,login_pass;
    private LinearLayout login;
    private CheckBox autologin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        String flag = getsp();
        String username = getsp2();
        Log.i("xml","flag=========="+flag);
        if (flag.equals("true")){
            Intent in = new Intent();
            in.setClass(LoginActivity.this, MainActivity.class);
            in.putExtra("username",username);


            finish();
            startActivity(in);

        }else {

            initview();

        }


    }

    private void initview() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_name.setFocusable(true);
        login_name.setFocusableInTouchMode(true);
        login_name.requestFocus();
        login_pass = (EditText) findViewById(R.id.login_pass);
        login = (LinearLayout) findViewById(R.id.login);
        autologin = (CheckBox) findViewById(R.id.autologin);
        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String lname = login_name.getText().toString();
                String pass = login_pass.getText().toString();
                CheckLogin cl = new CheckLogin(lname, pass);
                cl.execute();
                cl.setOnDataFinishedListener(new OnDataFinishedListener() {
                    @Override
                    public void onDataSuccessfully(Object data) {
                        String checked = data.toString();
                        Log.i("xml", "truetruetruetruetruetruetruetrue" + checked);
                        Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                        if (autologin.isChecked()) {
                            savesp();
                        }
                        savesp2();
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                    @Override
                    public void onDataFailed() {
                        Toast.makeText(LoginActivity.this, "请输入正确的用户名和密码！", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }
    private void savesp(){
        SharedPreferences preferences = getSharedPreferences("autologin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = preferences.edit();
        editor.putString("autologin", "true");
        editor.commit();

    }
    private void savesp2(){
        SharedPreferences preferences = getSharedPreferences("autologin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = preferences.edit();
        editor.putString("username", login_name.getText().toString());
        editor.commit();

    }

    private String getsp(){
        SharedPreferences preferences = getSharedPreferences("autologin", Context.MODE_PRIVATE);
        String flag = preferences.getString("autologin", "false");
        return flag;
    }
    private String getsp2(){
        SharedPreferences preferences = getSharedPreferences("autologin", Context.MODE_PRIVATE);
        String flag = preferences.getString("username", "false");
        return flag;
    }
    /**
     * 判断网络状态
     * @param context
     * @return
     */
    public static boolean checkNetWorkStatus(Context context){
        boolean result;
        ConnectivityManager cm=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if ( netinfo !=null && netinfo.isConnected() ) {
            result=true;
            Log.i("xml", "The net was connected");
        }else{
            result=false;
            Log.i("xml", "The net was bad!");
        }
        return result;
    }
}
