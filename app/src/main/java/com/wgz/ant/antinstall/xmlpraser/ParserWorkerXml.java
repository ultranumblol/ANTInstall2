package com.wgz.ant.antinstall.xmlpraser;

import android.os.AsyncTask;
import android.util.Log;

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
    private int state;
    public ParserWorkerXml(String username,int state) {
        super();
        this.username = username;
        this.state=state;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        wors = new ArrayList<Map<String,Object>>();
        SignMaker sm = new SignMaker();
        String sign= sm.getsign("username="+username,state);
            XmlInputStream xmlInputStream = new XmlInputStream();
        InputStream is = xmlInputStream.getStream(username,state+"",sign);
        WorkerParser wparser = new PullPraserWorker();
        try {
            mWorker = wparser.parse(is);
            for (Worker worker:mWorker){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("workID",worker.getWorkID());
                map.put("aznumber",worker.getAzdispatchingnumber());
                map.put("workerName",worker.getWorkerName());
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
        Log.i("xml","result======="+result.size());

        if(result.size()==0){
            onDataFinishedListener.onDataFailed();

        }else{
            onDataFinishedListener.onDataSuccessfully(result);
        }
    }
}
