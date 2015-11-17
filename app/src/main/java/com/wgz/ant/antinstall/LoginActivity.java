package com.wgz.ant.antinstall;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

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
        initview();

    }

    private void initview() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_pass = (EditText) findViewById(R.id.login_pass);
        login = (LinearLayout) findViewById(R.id.login);
        autologin = (CheckBox) findViewById(R.id.autologin);
        login.setOnClickListener(new View.OnClickListener() {
            String lname = login_name.getText().toString();
            String pass = login_pass.getText().toString();
            @Override
            public void onClick(View v) {

            }
        });


    }
    private void savesp(){
        SharedPreferences preferences = getSharedPreferences("autologin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = preferences.edit();
        editor.putString("autologin", "true");
        editor.commit();

    }
    private String getsp(){
        SharedPreferences preferences = getSharedPreferences("autologin", Context.MODE_PRIVATE);
        String flag = preferences.getString("autologin","false");
        return flag;
    }
}
