package com.wgz.ant.antinstall.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 通过url连接服务器 将返回的xml文件变成流
 * Created by qwerr on 2015/11/27.
 */
public class XmlInputStream {
    public InputStream getStream(String type,String id,String state,String sign,String username,String remark,String code){

        String path ="";

        if (state==null){
            String path2 = "http://wuliu.chinaant.com/AppInstallationEDI.aspx?"+"id="+id+"&"+"type="+type+"&sign="+sign;
            path = path2;
        }if(remark==null&&state!=null)  {

            String path2 = "http://wuliu.chinaant.com/AppInstallationEDI.aspx?"+"id="+id+"&"+"type="+type+"&state="+state+"&sign="+sign;
            path = path2;
        }if(remark!=null&&code==null){
            String path2 = null;
            try {
                path2 = "http://wuliu.chinaant.com/AppInstallationEDI.aspx?"+"id="+id+"&"+"type="+type+"&state="+state+"&username="+username+"&remark="+ URLEncoder.encode(remark, "UTF-8")+"&sign="+sign;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            path = path2;

        }
        if(code!=null){
            String path2 = null;
            try {
                path2 = "http://wuliu.chinaant.com/AppInstallationEDI.aspx?"+"id="+id+"&"+"type="+type+"&state="+state+"&code="+ URLEncoder.encode(code, "UTF-8")+"&sign="+sign;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            path = path2;
        }

        Log.i("xml2", "XML==path：" + path);
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

        conn.setReadTimeout(5*1000);
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inStream;

    }
    public InputStream getStream(String username,String state,String sign){


        String path2 = "http://wuliu.chinaant.com/AppDespacthingInfo.aspx?username="+username+"&state="+state+"&sign="+sign;




        Log.i("xml2", "XML==path：" + path2);
        URL myURL = null;
        try {
            myURL = new URL(
                    path2);
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

        conn.setReadTimeout(5*1000);
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inStream;

    }
    public InputStream getStream(String username,String oldpassword,String newpassword,String sign){


        String path2 = "http://wuliu.chinaant.com/AppChangePassword.aspx?username="+username+"&oldpassword="+oldpassword+"&newpassword="+newpassword+"&sign="+sign;




        Log.i("xml2", "XML==path：" + path2);
        URL myURL = null;
        try {
            myURL = new URL(
                    path2);
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

        conn.setReadTimeout(5*1000);
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return inStream;

    }

}
