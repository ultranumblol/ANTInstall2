package com.wgz.ant.antinstall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * Created by qwerr on 2015/11/18.
 */
public class OrderActivity extends Activity {
    private LinearLayout back;

    private LinearLayout orderfinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.order);
        initview();
    }

    private void initview() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("order");
        String orderid = bundle.getString("id");
        String ordertype = bundle.getString("type");


        back = (LinearLayout) findViewById(R.id.order_back);

       // orderfinish = (LinearLayout) findViewById(R.id.order_finish);
       /* if (ordertype.equals("1")){
            orderfinish.setVisibility(View.GONE);

        }if (ordertype.equals("0")){
            orderfinish.setVisibility(View.VISIBLE);

        }*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
