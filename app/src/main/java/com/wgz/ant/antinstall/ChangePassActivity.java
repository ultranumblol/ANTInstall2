package com.wgz.ant.antinstall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wgz.ant.antinstall.util.ChangePass;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;

import java.io.File;

/**
 * Created by qwerr on 2015/12/30.
 */
public class ChangePassActivity extends Activity {
    private EditText oldpass,newpass,againnewpass;
    private Button mOkBtn,mCanBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepass);
        initview();
    }
    private String getsp2(){
        SharedPreferences preferences =getSharedPreferences("autologin", Context.MODE_PRIVATE);
        String flag = preferences.getString("username", "false");
        return flag;
    }
    private void initview() {
        oldpass = (EditText) findViewById(R.id.oldpass);
        newpass = (EditText) findViewById(R.id.newpass);
        againnewpass = (EditText) findViewById(R.id.againnewpass);
        mOkBtn = (Button) findViewById(R.id.cp_ok);
        mCanBtn = (Button) findViewById(R.id.cp_cancel);
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldp = oldpass.getText().toString();
                String newp = newpass.getText().toString();
                String newp2 = againnewpass.getText().toString();
                if (!newp.equals(newp2)){
                    Toast.makeText(getApplicationContext(),"两次密码不一致！",Toast.LENGTH_SHORT).show();
                }else{
                    ChangePass cp = new ChangePass(getsp2(),oldp,newp);
                    cp.execute();
                    cp.setOnDataFinishedListener(new OnDataFinishedListener() {
                        @Override
                        public void onDataSuccessfully(Object data) {

                            File file= new File("/data/data/"+getPackageName().toString()+"/shared_prefs","autologin.xml");
                            if(file.exists()){
                                file.delete();
                                SharedPreferences sp = getSharedPreferences("autologin", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.clear().commit();
                                SharedPreferences sp2 = getSharedPreferences("username", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sp2.edit();
                                editor2.clear().commit();}
                            Toast.makeText(getApplicationContext(),"修改成功，请重新登录！",Toast.LENGTH_LONG).show();
                            ChangePassActivity.this.finish();
                            startActivity(new Intent(ChangePassActivity.this,LoginActivity.class));
                        }

                        @Override
                        public void onDataFailed() {
                            Toast.makeText(getApplicationContext(),"错误，请检查密码！",Toast.LENGTH_LONG).show();

                        }
                    });


                }


            }
        });
        mCanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
