package com.wgz.ant.antinstall.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.wgz.ant.antinstall.OrderActivity;
import com.wgz.ant.antinstall.R;
import com.wgz.ant.antinstall.view.RefreshableView;

import java.util.ArrayList;
import java.util.HashMap;
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
        tuotoulv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),testData(),R.layout.order_lv_item,
                new String[]{"id","title","type"},new int[]{R.id.order_id,R.id.order_content,R.id.order_type}));
        untuotoulv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),testData2(),R.layout.order_lv_item,
                new String[]{"id","title","type"},new int[]{R.id.order_id,R.id.order_content,R.id.order_type}));
        tuotoulv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView orderId = (TextView) view.findViewById(R.id.order_id);
                TextView orderType = (TextView) view.findViewById(R.id.order_type);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("id",orderId.getText().toString());
                bundle.putString("type",orderType.getText().toString());
                intent.putExtra("order", bundle);
                intent.setClass(getActivity(),OrderActivity.class);
                startActivity(intent);

            }
        });
        untuotoulv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView orderId = (TextView) view.findViewById(R.id.order_id);
                TextView orderType = (TextView) view.findViewById(R.id.order_type);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("id",orderId.getText().toString());
                bundle.putString("type",orderType.getText().toString());
                intent.putExtra("order", bundle);
                intent.setClass(getActivity(),OrderActivity.class);
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

    private List<Map<String,Object>> testData(){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i<7; i++){
            Map<String, Object> map = new HashMap<String , Object>();
            map.put("id","DJLKHDAS0"+i);
            map.put("title","妥投业务"+i);
            map.put("type","1");
            list.add(map);

        }

      return  list;
    }
    private List<Map<String,Object>> testData2(){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i<7; i++){
            Map<String, Object> map = new HashMap<String , Object>();
            map.put("id","JFJDOFSJO"+i);
            map.put("title","未妥投业务"+i);
            map.put("type","0");
            list.add(map);

        }

        return  list;
    }
}
