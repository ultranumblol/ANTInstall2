package com.wgz.ant.antinstall.util;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by qwerr on 2015/12/30.
 */
public class ChangePass extends AsyncTask {
    OnDataFinishedListener onDataFinishedListener;
    private String name,oldpassword,newpassword;


    public ChangePass(String name,String oldpassword,String newpassword) {
        super();
        this.name=name;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        SignMaker sm = new SignMaker();//实例化
        //通过用户名，密码制作sign
        String sign =sm.signCP("username="+name,"oldpassword="+oldpassword,"newpassword="+newpassword);
        Log.i("xml", "签名是：" + sign);

        PostForInputstream postForInputstream = new PostForInputstream();
        InputStream  is = postForInputstream.changePassInputStream(name,oldpassword,newpassword,sign);

        //XmlInputStream xmlInputStream = new XmlInputStream();
        //InputStream is= xmlInputStream.getStream(name,oldpassword,newpassword,sign);
        //Log.i("xml", "isisisisis：" + is.toString());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(is==null){
            String str ="请求错误！";
            return str;
        }else{

            int i;
            try {
                while ((i = is.read()) != -1) {
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
        Log.i("xml", "changgepassssss" + pass);
        if(pass.contains("修改成功!")){
            onDataFinishedListener.onDataSuccessfully(o);


        }else{
            onDataFinishedListener.onDataFailed();
        }
    }
}
