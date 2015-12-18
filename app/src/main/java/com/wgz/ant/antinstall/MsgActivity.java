package com.wgz.ant.antinstall;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * Created by qwerr on 2015/12/14.
 */
public class MsgActivity extends Activity {
    private LinearLayout back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.msg);

        initView();
    }

    private void initView() {
        back = (LinearLayout) findViewById(R.id.msg_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
