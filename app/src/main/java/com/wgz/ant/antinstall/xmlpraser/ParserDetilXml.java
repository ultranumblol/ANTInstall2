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

    public ParserDetilXml(String type, String id, String state) {
        this.type = type;
        this.id = id;
        this.state = state;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        dets = new ArrayList<Map<String,Object>>();

        SignMaker sm = new SignMaker();
        String sign1 = "";
        if (state==null){

            String sign = sm.getsign("type="+type,"id="+id);
            sign1=sign;

        }else {
            String sign = sm.getsign("type="+type,"id="+id,"state="+state);
            sign1=sign;
        }
        XmlInputStream xmlInputStream = new XmlInputStream();
        InputStream is = xmlInputStream.getStream(type,id,state,sign1);
        DetailPraser dp = new PullPraserDetail();
        try {
            mdetail = dp.parse(is);
            for (Detail detail:mdetail){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name",detail.getName());
                map.put("address",detail.getAddress());
                map.put("phone",detail.getPhone());
                map.put("privce",detail.getPrice());
                map.put("name1",detail.getGoodname());
                map.put("goodsmoney",detail.getGoodsmoeny());
                map.put("quantity",detail.getCount());
                map.put("aznumber",detail.getAznumber());
                map.put("azreservation",detail.getAzreservation());
                map.put("delivery",detail.getDelivery());
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

        }if (result.size()!=0){
            onDataFinishedListener.onDataSuccessfully(result);
        }
    }
}
