package com.wgz.ant.antinstall.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wgz.ant.antinstall.MsgActivity;
import com.wgz.ant.antinstall.R;
import com.wgz.ant.antinstall.adapter.OrderAdapter;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;
import com.wgz.ant.antinstall.view.RefreshableView;
import com.wgz.ant.antinstall.xmlpraser.ParserWorkerXml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2015/11/16.
 */
public class OrderFragment extends Fragment {
    private TextView tuotou,untuotou;
    private ListView tuotoulv,untuotoulv;
    private RefreshableView refreshableView2,refreshableView3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderfragment,null);
        intiview(view);
        return  view;
    }

    private void intiview(final View view) {
        refreshableView2 = (RefreshableView) view.findViewById(R.id.refreshable_view2);
        refreshableView3 = (RefreshableView) view.findViewById(R.id.refreshable_view3);
        tuotou = (TextView) view.findViewById(R.id.tuotou_tv);
        untuotou = (TextView) view.findViewById(R.id.untuotou_tv);
        tuotoulv = (ListView) view.findViewById(R.id.tuotou_lv);
        untuotoulv = (ListView) view.findViewById(R.id.untuotou_lv);
        //tuotoulv.setAdapter(new OrderAdapter(testData(),getActivity()));
        //untuotoulv.setAdapter(new OrderAdapter(testData2(),getActivity()));
        initData();
        initData2();
        /*tuotoulv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),testData(),R.layout.order_lv_item,
                new String[]{"id","title","type"},new int[]{R.id.order_id,R.id.order_content,R.id.order_type}));
        untuotoulv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),testData2(),R.layout.order_lv_item,
                new String[]{"id","title","type"},new int[]{R.id.order_id,R.id.order_content,R.id.order_type}));*/
        tuotoulv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView workid = (TextView) view.findViewById(R.id.work_id);

                Intent intent = new Intent();
                intent.putExtra("workID",workid.getText().toString());
                intent.putExtra("order",true);
                intent.setClass(getActivity(),MsgActivity.class);
                startActivity(intent);

            }
        });
        untuotoulv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView workid = (TextView) view.findViewById(R.id.work_id);

                Intent intent = new Intent();
                intent.putExtra("order",true);
                intent.putExtra("workID",workid.getText().toString());

                intent.setClass(getActivity(),MsgActivity.class);
                startActivity(intent);
            }
        });




        refreshableView2.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView2.finishRefreshing();
            }
        }, 2);

        refreshableView3.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView3.finishRefreshing();
            }
        },3);




        tuotou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuotou.setBackgroundResource(R.drawable.bordergray_table_leftradius_ontrans_pressed);
                untuotou.setBackgroundResource(R.drawable.bordergray_table_rightradius_ontrans);
                tuotou.setTextColor(Color.WHITE);
                untuotou.setTextColor(android.graphics.Color.parseColor("#ff0000"));
                refreshableView3.setVisibility(View.VISIBLE);
                refreshableView2.setVisibility(View.GONE);
            }
        });
        untuotou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                untuotou.setBackgroundResource(R.drawable.bordergray_table_rightradius_ontrans_pressed);
                tuotou.setBackgroundResource(R.drawable.bordergray_table_leftradius_ontrans);
                untuotou.setTextColor(Color.WHITE);
                tuotou.setTextColor(android.graphics.Color.parseColor("#ff0000"));
                refreshableView2.setVisibility(View.VISIBLE);
                refreshableView3.setVisibility(View.GONE);
            }
        });
    }
    private String getsp2(){
        SharedPreferences preferences = getActivity().getSharedPreferences("autologin", Context.MODE_PRIVATE);
        String flag = preferences.getString("username", "false");
        return flag;
    }

    /*
    * 初始化数据
    * */
    private void initData(){
        ParserWorkerXml pw = new ParserWorkerXml(getsp2(),1);
        pw.execute();
        pw.setOnDataFinishedListener(new OnDataFinishedListener() {
            @Override
            public void onDataSuccessfully(Object data) {
                List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
                list1 = (List<Map<String, Object>>) data;
                Log.i("xml","list1111==="+list1.toString());
                tuotoulv.setAdapter(new OrderAdapter(list1,getActivity(),1));
            }

            @Override
            public void onDataFailed() {
                //Toast.makeText(getActivity(),"没有相关数据!",Toast.LENGTH_LONG).show();
            }
        });



    }
    private void initData2(){
        ParserWorkerXml pw = new ParserWorkerXml(getsp2(),2);
        pw.execute();
        pw.setOnDataFinishedListener(new OnDataFinishedListener() {
            @Override
            public void onDataSuccessfully(Object data) {
                List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
                list1 = (List<Map<String, Object>>) data;
                Log.i("xml","list222==="+list1.toString());
                untuotoulv.setAdapter(new OrderAdapter(list1,getActivity(),2));
            }

            @Override
            public void onDataFailed() {
                // Toast.makeText(getActivity(),"没有相关数据!",Toast.LENGTH_LONG).show();
            }
        });



    }


}
