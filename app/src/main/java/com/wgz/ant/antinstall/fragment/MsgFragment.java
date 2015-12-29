package com.wgz.ant.antinstall.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wgz.ant.antinstall.MsgActivity;
import com.wgz.ant.antinstall.R;
import com.wgz.ant.antinstall.adapter.MsgFmtAdapter;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;
import com.wgz.ant.antinstall.view.RefreshableView;
import com.wgz.ant.antinstall.xmlpraser.ParserWorkerXml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2015/11/16.
 */
public class MsgFragment extends Fragment {
    RefreshableView refreshableView;
    private ListView msglv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msgfragment, null);
        initview(view);
        initData();
        return view;
    }

    private void initview(View view) {
        msglv = (ListView) view .findViewById(R.id.msg_lv);
        refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);



        /*msglv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),CeshiDATA(),
                R.layout.msglv_item,new String[]{"name"},new int[]{R.id.msg_workerName}));*/
        msglv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(), MsgActivity.class));
            }
        });
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    Thread.sleep(2000);
                    initData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        },0);

    }
    private List<Map<String,Object>> CeshiDATA() {
         List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
        for (int i = 0;i<5;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("name","张"+(i+1));
            list1.add(map);

        }
        return list1;
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
        String username=getsp2();

        ParserWorkerXml pw = new ParserWorkerXml(username,0);
        pw.execute();
        pw.setOnDataFinishedListener(new OnDataFinishedListener() {
            @Override
            public void onDataSuccessfully(Object data) {
                List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
                list1 = (List<Map<String, Object>>) data;
                Log.i("xml","list1==="+list1.toString());
               msglv.setAdapter(new MsgFmtAdapter(list1,getContext()));
            }

            @Override
            public void onDataFailed() {
                Toast.makeText(getActivity(),"没有相关数据!",Toast.LENGTH_LONG).show();
            }
        });



    }

}
