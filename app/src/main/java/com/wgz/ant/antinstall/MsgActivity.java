package com.wgz.ant.antinstall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2015/12/14.
 */
public class MsgActivity extends Activity {
    private LinearLayout back ;
    private ListView msg_lv;
    private LinearLayout btnlay;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.msg);

        initView();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.msg_title);
        Intent intent = getIntent();
        boolean flag =  intent.getBooleanExtra("order",false);

        btnlay = (LinearLayout) findViewById(R.id.btn_layout);
        if (flag==true){
            btnlay.setVisibility(View.GONE);
            title.setText("订单详情");

        }else {btnlay.setVisibility(View.VISIBLE);
            title.setText("消息详情");
        }


        back = (LinearLayout) findViewById(R.id.msg_back);
        msg_lv = (ListView) findViewById(R.id.id_goods_list);
msg_lv.setAdapter(new SimpleAdapter(getApplicationContext(),CeshiDATA(),R.layout.goods_lv_item,new String[]{"name","count","type","money"},

        new int[]{R.id.id_goods_name,R.id.id_goods_num,R.id.id_goods_type,R.id.id_goods_price}));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private List<Map<String,Object>> CeshiDATA() {
        List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
        for (int i = 0;i<8;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("name","货物"+(i+1));
            map.put("count","1");
            map.put("type","家具组装");
            map.put("money",""+(int)(Math.random()*500));
            list1.add(map);

        }
        return list1;
    }
}
