package com.wgz.ant.antinstall.xmlpraser;

import android.os.AsyncTask;
import android.util.Log;

import com.wgz.ant.antinstall.util.XmlInputStream;

import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2016/1/6.
 */
public class PraseXmlBackground extends AsyncTask {
    AsynCallBack asynCallBack;
    private List<Map<String,Object>> mData;
    private String type,id,state,sign,username,remark,code;

    public PraseXmlBackground(String type, String id, String state, String sign, String username, String remark, String code) {
        this.type = type;
        this.id = id;
        this.state = state;
        this.sign = sign;
        this.username = username;
        this.remark = remark;
        this.code = code;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        PraserXml px = new PraserXml();
        try {
           mData= px.prase(new XmlInputStream().getStream(type,id,state,sign,username,remark,code));
            Log.i("xxml","Mdata:"+mData.toString());

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return mData;
    }
public void  setOnDataCallBack(AsynCallBack asynCallBack){
    this.asynCallBack = asynCallBack;

}
    @Override
    protected void onPostExecute(Object o) {
        List<Map<String, Object>> result = (List<Map<String, Object>>) o;
        if (result.size()==0){
            asynCallBack.onDatafaild();
        }
        if (result.size()!=0){
            asynCallBack.onDatasucess(result);
        }
    }
}
