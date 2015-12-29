package com.wgz.ant.antinstall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.wgz.ant.antinstall.util.OnDataFinishedListener;
import com.wgz.ant.antinstall.xmlpraser.ParserDetilXml;

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
    private TextView title,wancheng,unwanchang,showinmap;
    private TextView orderID,name,phone,servType,address,money,delivery,azreservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.msg);

        initView();
        initData();
    }
        //初始化订单数据
    private void initData() {
        Intent intent = getIntent();

        String workID = intent.getStringExtra("workID");

        ParserDetilXml pd = new ParserDetilXml("get",workID,null);
        pd.execute();
        pd.setOnDataFinishedListener(new OnDataFinishedListener() {
            @Override
            public void onDataSuccessfully(Object data) {
                List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
                list1= (List<Map<String, Object>>) data;
                orderID.setText(list1.get(0).get("aznumber").toString());
                name.setText(list1.get(0).get("name").toString());
                phone.setText(list1.get(0).get("phone").toString());

                address.setText(list1.get(0).get("address").toString());
                money.setText(list1.get(0).get("privce").toString());
                azreservation.setText(list1.get(0).get("azreservation").toString());
                delivery.setText(list1.get(0).get("delivery").toString());



                msg_lv.setAdapter(new SimpleAdapter(MsgActivity.this,list1,R.layout.goods_lv_item,new String[]{"name1","quantity","goodsmoney"},

                        new int[]{R.id.id_goods_name,R.id.id_goods_num,R.id.id_goods_price}));



                Log.i("xml","msg====="+list1.toString());
            }

            @Override
            public void onDataFailed() {

            }
        });

    }

    private void initView() {
        orderID = (TextView) findViewById(R.id.id_order_id);
        name = (TextView) findViewById(R.id.id_order_name);
        phone = (TextView) findViewById(R.id.id_order_phone);
        servType = (TextView) findViewById(R.id.id_order_servicetype);
        address = (TextView) findViewById(R.id.id_order_address);
        money = (TextView) findViewById(R.id.id_order_anzhuangfei);
        azreservation = (TextView) findViewById(R.id.id_order_preTime);
        delivery = (TextView) findViewById(R.id.id_order_time);


        title = (TextView) findViewById(R.id.msg_title);
        wancheng = (TextView) findViewById(R.id.Tv_wancheng);
        unwanchang = (TextView) findViewById(R.id.Tv_unwancheng);
        showinmap = (TextView) findViewById(R.id.Tv_showInMap);
        showinmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("endAddress",address.getText().toString());
                intent.setClass(MsgActivity.this,NewMapActivity.class);
                startActivity(intent);
            }
        });
        //销单已完成订单
        wancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MsgActivity.this);
                builder.setTitle("确认").setMessage("请确认完成").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("取消",null);
                builder.show();





                ParserDetilXml pd = new ParserDetilXml("set","001","1");
                pd.execute();
                pd.setOnDataFinishedListener(new OnDataFinishedListener() {
                    @Override
                    public void onDataSuccessfully(Object data) {

                    }

                    @Override
                    public void onDataFailed() {

                    }
                });

            }
        });
        //销单未完成订单
        unwanchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText inputServer = new EditText(MsgActivity.this);
                inputServer.setMinLines(3);
                AlertDialog.Builder builder = new AlertDialog.Builder(MsgActivity.this);
                builder.setTitle("请输入未完成原因：").setView(inputServer)
                        .setNegativeButton("取消", null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        String reason = inputServer.getText().toString();



                    }
                });
                builder.show();





            ParserDetilXml pd = new ParserDetilXml("set","001","2");
                pd.execute();
                pd.setOnDataFinishedListener(new OnDataFinishedListener() {
                    @Override
                    public void onDataSuccessfully(Object data) {

                    }

                    @Override
                    public void onDataFailed() {

                    }
                });
            }
        });

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
/*msg_lv.setAdapter(new SimpleAdapter(getApplicationContext(),CeshiDATA(),R.layout.goods_lv_item,new String[]{"name","count","type","money"},

        new int[]{R.id.id_goods_name,R.id.id_goods_num,R.id.id_goods_type,R.id.id_goods_price}));*/
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
