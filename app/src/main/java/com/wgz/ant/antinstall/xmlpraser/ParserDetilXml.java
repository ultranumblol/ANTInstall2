package com.wgz.ant.antinstall.xmlpraser;

import android.os.AsyncTask;

import com.wgz.ant.antinstall.bean.Detail;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;
import com.wgz.ant.antinstall.util.SignMaker;
import com.wgz.ant.antinstall.util.XmlInputStream;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2015/12/24.
 */
public class ParserDetilXml extends AsyncTask {
    private List<Detail> mdetail;
    private List<Map<String,Object>> dets;
    OnDataFinishedListener onDataFinishedListener;
    private String type;
    private String id;
    private String state;


    @Override
    protected Object doInBackground(Object[] params) {
        dets = new ArrayList<Map<String,Object>>();
        SignMaker sm = new SignMaker();
        String sign = sm.getsign(type,id,state);
        XmlInputStream xmlInputStream = new XmlInputStream();
        InputStream is = xmlInputStream.getStream(type,id,state,sign);
        DetailPraser dp = new PullPraserDetail();
        try {
            mdetail = dp.parse(is);
            for (Detail detail:mdetail){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name",detail.getName());
                map.put("address",detail.getAddress());
                dets.add(map);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return dets;
    }
}
