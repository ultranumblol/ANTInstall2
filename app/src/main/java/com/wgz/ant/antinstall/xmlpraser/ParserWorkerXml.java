package com.wgz.ant.antinstall.xmlpraser;

import android.os.AsyncTask;

import com.wgz.ant.antinstall.bean.Worker;
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
public class ParserWorkerXml extends AsyncTask {
    private List<Worker> mWorker;
    private List<Map<String,Object>> wors;
    OnDataFinishedListener onDataFinishedListener;
    private String username;

    public ParserWorkerXml(String username) {
        super();
        this.username = username;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        wors = new ArrayList<Map<String,Object>>();
        SignMaker sm = new SignMaker();
        String sign= sm.getsign(username);
            XmlInputStream xmlInputStream = new XmlInputStream();
        InputStream is = xmlInputStream.getStream(username,sign);
        WorkerParser wparser = new PullPraserWorker();
        try {
            mWorker = wparser.parse(is);
            for (Worker worker:mWorker){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("workername",worker.getWorkername());
                map.put("wordID",worker.getWorkerID());
                wors.add(map);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return wors;
    }
    public void setOnDataFinishedListener(
            OnDataFinishedListener onDataFinishedListener) {
        this.onDataFinishedListener = onDataFinishedListener;
    }

    @Override
    protected void onPostExecute(Object o) {
        List<Map<String, Object>> result = (List<Map<String, Object>>) o;

        if(result!=null){
            onDataFinishedListener.onDataSuccessfully(result);
        }else{
            onDataFinishedListener.onDataFailed();
        }
    }
}
