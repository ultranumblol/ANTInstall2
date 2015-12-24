package com.wgz.ant.antinstall.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by qwerr on 2015/11/11.
 */
public class CheckpassInputstream {
    /**
     * 通过url 获得服务器返回的xml数据，转换成InputStream中
     * @param username 姓名
     * @param userpassword 密码
     * @param sign 签名
     * @return
     */
    public InputStream getStream(String username,String userpassword,String sign){
        // 定义获取文件内容的URL
        //String path = "http://hr.chinaant.com/xmlhandler.aspx?username=123&password=123&sign=5B7D1F8CEFBF1C80AEB73329C8A378A1";
        String path ="";
        String path2 = "http://wuliu.chinaant.com/AppHandler.aspx?"+username+"&"+userpassword+"&sign="+sign;


        Log.i("xml", path2);
        URL myURL = null;
        try {
            myURL = new URL(
                    path);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)myURL.openConnection();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //设置请求超时与请求方式
        conn.setReadTimeout(5*1000);
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //从链接中获取一个输入流对象
        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //对输入流进行解析
        // Log.i("xml", inStream.toString()+"获得输入流");
        return inStream;

    }
}
