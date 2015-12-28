package com.wgz.ant.antinstall.xmlpraser;

import android.os.AsyncTask;
import android.util.Log;

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
        String sign = sm.getsign("type="+type,"id="+id,"state="+state);
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
    public void setOnDataFinishedListener(
            OnDataFinishedListener onDataFinishedListener) {
        this.onDataFinishedListener = onDataFinishedListener;
    }
    @Override
    protected void onPostExecute(Object o) {
        List<Map<String, Object>> result = (List<Map<String, Object>>) o;
        Log.i("xml","result======="+result.size());

        if(result.size()==0){
            onDataFinishedListener.onDataFailed();

        }else{
            onDataFinishedListener.onDataSuccessfully(result);
        }
    }
}
