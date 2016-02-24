package com.wgz.ant.antinstall.xmlpraser;

import android.os.AsyncTask;
import android.util.Log;

import com.wgz.ant.antinstall.util.PostForInputstream;
import com.wgz.ant.antinstall.util.SignMaker;

import org.dom4j.DocumentException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by qwerr on 2016/1/6.
 */
public class PraseXmlBackground extends AsyncTask {
    AsynCallBack asynCallBack;
    private List<Map<String,Object>> mData;
    private String type,id,state,username,remark,code;

    public PraseXmlBackground(String type, String id, String state,  String username, String remark, String code) {
        this.type = type;
        this.id = id;
        this.state = state;

        this.username = username;
        this.remark = remark;
        this.code = code;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        PraserXml px = new PraserXml();
        try {
            SignMaker sm = new SignMaker();
            String sign = sm.getsign("type="+type,"id="+id);
            PostForInputstream pfi = new PostForInputstream();
            InputStream is2 =pfi.getStream2(type,id,state,sign,username,remark,code);
           mData= px.prase(is2);
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
       // Log.i("xxml","o======="+o.toString());
        List<Map<String, Object>> result = (List<Map<String, Object>>) o;
        //Log.i("xxml","result:"+result.toString());
        asynCallBack.onDatasucess(result);
    }
}
