package com.wgz.ant.antinstall.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.wgz.ant.antinstall.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2015/11/16.
 */
public class MsgFragment extends Fragment {
    private ListView msglv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.msgfragment, null);
        initview(view);
        return view;
    }

    private void initview(View view) {
        msglv = (ListView) view .findViewById(R.id.msg_lv);
        msglv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),CeshiDATA(),
                R.layout.msglv_item,new String[]{"sys"},new int[]{R.id.msglv_tv}));

    }
    private List<Map<String,Object>> CeshiDATA() {
         List<Map<String, Object>> list1 = new ArrayList<Map<String,Object>>();
        for (int i = 0;i<5;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("sys","系统消息"+(i+1));
            list1.add(map);

        }
        return list1;
    }

}
