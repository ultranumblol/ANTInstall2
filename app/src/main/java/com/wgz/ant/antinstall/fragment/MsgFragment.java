package com.wgz.ant.antinstall.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wgz.ant.antinstall.MsgActivity;
import com.wgz.ant.antinstall.R;
import com.wgz.ant.antinstall.view.RefreshableView;

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
        return view;
    }

    private void initview(View view) {
        msglv = (ListView) view .findViewById(R.id.msg_lv);
        refreshableView = (RefreshableView) view.findViewById(R.id.refreshable_view);
        msglv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),CeshiDATA(),
                R.layout.msglv_item,new String[]{"name"},new int[]{R.id.msglv_tv}));
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

}
