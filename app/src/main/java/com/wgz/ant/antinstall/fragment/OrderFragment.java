package com.wgz.ant.antinstall.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.wgz.ant.antinstall.R;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderfragment,null);
        intiview(view);
        return  view;
    }

    private void intiview(final View view) {

        tuotou = (TextView) view.findViewById(R.id.tuotou_tv);
        untuotou = (TextView) view.findViewById(R.id.untuotou_tv);
        tuotoulv = (ListView) view.findViewById(R.id.tuotou_lv);
        untuotoulv = (ListView) view.findViewById(R.id.untuotou_lv);
        tuotoulv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),testData(),R.layout.order_lv_item,
                new String[]{"id","title"},new int[]{R.id.order_id,R.id.order_content}));
        untuotoulv.setAdapter(new SimpleAdapter(getActivity().getApplicationContext(),testData2(),R.layout.order_lv_item,
                new String[]{"id","title"},new int[]{R.id.order_id,R.id.order_content}));








        tuotou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tuotoulv.setVisibility(View.VISIBLE);
                untuotoulv.setVisibility(View.GONE);
            }
        });
        untuotou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                untuotoulv.setVisibility(View.VISIBLE);
                tuotoulv.setVisibility(View.GONE);
            }
        });
    }

    private List<Map<String,Object>> testData(){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0 ; i<7; i++){
            Map<String, Object> map = new HashMap<String , Object>();
            map.put("id","DJLKHDAS0"+i);
            map.put("title","妥投业务"+i);
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
            list.add(map);

        }

        return  list;
    }
}
