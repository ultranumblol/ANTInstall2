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
import android.widget.TextView;
import android.widget.Toast;

import com.wgz.ant.antinstall.MsgActivity;
import com.wgz.ant.antinstall.R;
import com.wgz.ant.antinstall.adapter.MsgFmtAdapter;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;
import com.wgz.ant.antinstall.view.RefreshableView;
import com.wgz.ant.antinstall.xmlpraser.ParserWorkerXml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2015/11/16.
 */
public class MsgFragment extends Fragment {
    RefreshableView refreshableView;
    private ListView msglv;
    private  List<Map<String, Object>> listDATE = new ArrayList<Map<String,Object>>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msgfragment, null);
        initview(view);
        initData();

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("xml","msggragment执行了回调刷新方法");
        initData();
        String result = data.getExtras().getString("result");
        if(result.equals("该刷新了")){

            Log.i("xml","msggragment执行了回调刷新方法");
            initData();
        }
    }
    private void initview(View view) {
        msglv = (ListView) view .findViewById(R.id.msg_lv);
        refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);
        msglv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView workid = (TextView) view.findViewById(R.id.order_ID);
                Intent intent = new Intent();
                intent.putExtra("workID",workid.getText().toString());
                intent.setClass(getActivity(),MsgActivity.class);

                startActivityForResult(intent,0);
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
                msglv.setAdapter(new MsgFmtAdapter(listDATE,getContext()));
                Toast.makeText(getActivity(),"没有相关业务!",Toast.LENGTH_SHORT).show();
            }
        });



    }

}
