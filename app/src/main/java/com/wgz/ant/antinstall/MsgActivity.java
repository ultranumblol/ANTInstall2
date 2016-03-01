package com.wgz.ant.antinstall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;
import com.wgz.ant.antinstall.xmlpraser.AsynCallBack;
import com.wgz.ant.antinstall.xmlpraser.ParserDetilXml;
import com.wgz.ant.antinstall.xmlpraser.PraseXmlBackground;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private TextView orderID,name,phone,servType,address,money,delivery,azreservation,pilot,pilotphone;
    private String workID,daohangAdd="";

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

        workID = intent.getStringExtra("workID");
//通用解析
        PraseXmlBackground pxb = new PraseXmlBackground("get",workID,null,null,null,null);
        pxb.execute();
        pxb.setOnDataCallBack(new AsynCallBack() {
            @Override
            public void onDatasucess(Object data) {
                List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
                list1= (List<Map<String, Object>>) data;
                orderID.setText(list1.get(0).get("aznumber").toString());
                name.setText(list1.get(0).get("name").toString());
                phone.setText(list1.get(0).get("phone1").toString());

                address.setText(list1.get(0).get("address").toString());

                double money3=Double.parseDouble(list1.get(0).get("price").toString());
                String money2 =formatDouble4(money3);
                money.setText(money2);
                azreservation.setText(list1.get(0).get("azreservation").toString());
                delivery.setText(list1.get(0).get("delivery").toString());
                servType.setText(list1.get(0).get("servertype").toString());
                try {
                    String pilophone1 =  list1.get(0).get("pilot").toString();
                    pilot.setText(pilophone1);
                } catch (Exception e) {

                    e.printStackTrace();
                    String pilophone1 =  "---";
                    pilot.setText(pilophone1);
                }
                try {
                   String pilophone1 =  list1.get(0).get("pilotphone").toString();
                    pilotphone.setText(pilophone1);
                } catch (Exception e) {

                    e.printStackTrace();
                    String pilophone1 =  "---";
                    pilotphone.setText(pilophone1);
                }



                msg_lv.setAdapter(new SimpleAdapter(MsgActivity.this,list1,R.layout.goods_lv_item,new String[]{"name1","quantity","goodsmoney","servicestype"},

                        new int[]{R.id.id_goods_name,R.id.id_goods_num,R.id.id_goods_price,R.id.id_goods_type}));



                Log.i("xmll","list1====="+list1.toString());
            }

            @Override
            public void onDatafaild() {
                Toast.makeText(getApplicationContext(),"1111",Toast.LENGTH_SHORT).show();
            }
        });


        /*ParserDetilXml pd = new ParserDetilXml("get",workID,null,null,null,null);
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
                servType.setText(list1.get(0).get("servertype").toString());
                pilot.setText(list1.get(0).get("pilot").toString());
                pilotphone.setText(list1.get(0).get("pilotphone").toString());


                msg_lv.setAdapter(new SimpleAdapter(MsgActivity.this,list1,R.layout.goods_lv_item,new String[]{"name1","quantity","goodsmoney","servicestype"},

                        new int[]{R.id.id_goods_name,R.id.id_goods_num,R.id.id_goods_price,R.id.id_goods_type}));



                Log.i("xmll","list1====="+list1.toString());
            }

            @Override
            public void onDataFailed() {

            }
        });*/

    }
    public static String formatDouble4(double d) {
        DecimalFormat df = new DecimalFormat("#.00");


        return df.format(d);
    }
    @Override
    public void finish() {
            //数据是使用Intent返回
            Intent intent = new Intent();
            //把返回数据存入Intent
            intent.putExtra("result", "该刷新了");
            //设置返回数据
            setResult(RESULT_OK, intent);
            super.finish();
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    private String getsp2(){
        SharedPreferences preferences = getSharedPreferences("autologin", Context.MODE_PRIVATE);
        String flag = preferences.getString("username", "????");
        return flag;
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
        pilot = (TextView) findViewById(R.id.id_pilot_name);
        pilotphone = (TextView) findViewById(R.id.id__pilot_phone);

        title = (TextView) findViewById(R.id.msg_title);
        wancheng = (TextView) findViewById(R.id.Tv_wancheng);
        unwanchang = (TextView) findViewById(R.id.Tv_unwancheng);
        showinmap = (TextView) findViewById(R.id.Tv_showInMap);
        //点击号码拨打电话
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(MsgActivity.this);
                dialog.setTitle("请确认").setMessage("是否拨打电话？").setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri
                                        .parse("tel:" +phone.getText().toString() ));
                                startActivity(dialIntent);
                            }
                        });
                dialog.show();



            }
        });

        //点击驾驶员号码拨打电话
        pilotphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(MsgActivity.this);
                dialog.setTitle("请确认").setMessage("是否拨打电话？").setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri
                                        .parse("tel:" +phone.getText().toString() ));
                                startActivity(dialIntent);
                            }
                        });
                dialog.show();



            }
        });

        //点击地址导航
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("endAddress",address.getText().toString());
                intent.setClass(MsgActivity.this,NewMapActivity.class);
                startActivity(intent);
            }
        });
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
                final EditText inputServer = new EditText(MsgActivity.this);
                LayoutInflater layoutInflater = LayoutInflater.from(MsgActivity.this);
                final View view = layoutInflater.inflate(R.layout.dialog_code_remark,null);


                AlertDialog.Builder builder = new AlertDialog.Builder(MsgActivity.this);
                builder.setTitle("确认完成").setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText mCode = (EditText) view.findViewById(R.id.id_complate_code);
                        EditText mRemark = (EditText) view.findViewById(R.id.id_complate_remark);
                        String remark2 = "";
                        String code2 = "";
                        if (mRemark.getText()==null){
                            String remark = "---";
                            String code = mCode.getText().toString().replaceAll( "\\s", "");
                            remark2 = remark;
                            code2 = code;
                        }else {
                            String code = mCode.getText().toString().replaceAll( "\\s", "");
                            String remark = mRemark.getText().toString().replaceAll( "\\s", "");
                            remark2 = remark;
                            code2 = code;
                        }



                        ParserDetilXml pd = new ParserDetilXml("set",workID,"1",null,remark2,code2);
                        pd.execute();
                        pd.setOnDataFinishedListener(new OnDataFinishedListener() {
                            @Override
                            public void onDataSuccessfully(Object data) {
                                Toast.makeText(MsgActivity.this,"操作完成",Toast.LENGTH_SHORT).show();
                                MsgActivity.this.finish();
                            }
                            @Override
                            public void onDataFailed() {
                                Toast.makeText(MsgActivity.this,"验证码有误",Toast.LENGTH_SHORT).show();
                                //MsgActivity.this.finish();
                            }
                        });
                    }
                }).setNegativeButton("取消",null);
                builder.show();







            }
        });
        //销单未完成订单
        unwanchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*String[] areas = new String[]{"客户不在家","丢失物品","容量不足","不恰当的服务条件"
                ,"付款问题","客户信息缺失","不正确或缺少图纸","缺失信息","丢失的文件","不正确的文章",
                        "损坏物品","预测量不正确","销售错误","安装误差","客户需求变化"};
                AlertDialog ad =new AlertDialog.Builder(MsgActivity.this).setTitle("请选择未完成类型：").setSingleChoiceItems(
                        areas,1,null).setNegativeButton("取消",null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogForReason();
                    }
                }).show();*/
DialogForReason();

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

    private void DialogForReason() {
    final EditText inputServer = new EditText(MsgActivity.this);
    inputServer.setMinLines(3);
    AlertDialog.Builder builder = new AlertDialog.Builder(MsgActivity.this);
    builder.setTitle("未完成原因备注：").setView(inputServer)
            .setNegativeButton("取消", null);
    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface dialog, int which) {

            String reason = inputServer.getText().toString().replaceAll( "\\s", "");
            ParserDetilXml pd = new ParserDetilXml("set",workID,"2",getsp2(),reason,null);
            pd.execute();
            pd.setOnDataFinishedListener(new OnDataFinishedListener() {
                @Override
                public void onDataSuccessfully(Object data) {
                    Toast.makeText(MsgActivity.this,"操作完成",Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onDataFailed() {
                    Toast.makeText(MsgActivity.this,"操作完成",Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


        }
    });
    builder.show();
    }

}
