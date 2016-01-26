package com.wgz.ant.antinstall.xmlpraser;

import android.os.AsyncTask;
import android.util.Log;

import com.wgz.ant.antinstall.bean.Worker;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;
import com.wgz.ant.antinstall.util.PostForInputstream;
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

        PostForInputstream pfi = new PostForInputstream();
        InputStream is2 =pfi.getStream(username,state+"",sign);

        //InputStream is = xmlInputStream.getStream(username,state+"",sign);
        WorkerParser wparser = new PullPraserWorker();
        try {
            mWorker = wparser.parse(is2);
            for (Worker worker:mWorker){
                Map<String, Object> map = new HashMap<String, Object>();
                if (worker.getWorkID()==null){
                    map.put("workID","---");
                }else {
                    map.put("workID",worker.getWorkID());

                }
                if (worker.getAzdispatchingnumber()==null){
                    map.put("aznumber","---");
                }else {

                    map.put("aznumber",worker.getAzdispatchingnumber());
                }
                if (worker.getWorkerName()==null){
                    map.put("workerName","---");
                }
                else {
                    map.put("workerName",worker.getWorkerName());

                }
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
