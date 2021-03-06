package com.wgz.ant.antinstall.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by qwerr on 2015/11/11.
 */
public class CheckLogin extends AsyncTask {
    OnDataFinishedListener onDataFinishedListener;
    private String name,pass;


    public CheckLogin(String name,String pass) {
        super();
        this.name=name;
        this.pass = pass;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        SignMaker sm = new SignMaker();//实例化
        String sign =sm.getsign("username="+name,"userpassword="+pass, null);//通过用户名，密码制作sign
        Log.i("xml", "签名是：" + sign);

        PostForInputstream pfi = new PostForInputstream();
        InputStream is2 = pfi.CheckpassInputStream(name,pass,sign);

        //CheckpassInputstream cpinputstream = new CheckpassInputstream();
        //InputStream is= cpinputstream.getStream("username="+name,"userpassword="+pass, sign);
        //Log.i("xml", "isisisisis：" + is.toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(is2==null){
            String str ="请求错误！";
            return str;
        }else{

            int i;
            try {
                while ((i = is2.read()) != -1) {
                    baos.write(i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String str = baos.toString();
            System.out.println(str);
            return str;

        }
    }
    public void setOnDataFinishedListener(
            OnDataFinishedListener onDataFinishedListener) {
        this.onDataFinishedListener = onDataFinishedListener;
    }

    @Override
    protected void onPostExecute(Object o) {
        String pass = o.toString();
        Log.i("xml", "passpasspasspasspasspasspass" + pass);
        if(pass.contains("true")){
            onDataFinishedListener.onDataSuccessfully(o);


        }else{
            onDataFinishedListener.onDataFailed();
        }
    }
}
