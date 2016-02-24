package com.wgz.ant.antinstall.xmlpraser;

import android.os.AsyncTask;
import android.util.Log;

import com.wgz.ant.antinstall.bean.Detail;
import com.wgz.ant.antinstall.util.OnDataFinishedListener;
import com.wgz.ant.antinstall.util.PostForInputstream;
import com.wgz.ant.antinstall.util.SignMaker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
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
    private String state,username,remark,code;

    public ParserDetilXml(String type, String id, String state,String username,String remark,String code) {
        this.type = type;
        this.id = id;
        this.state = state;
        this.username = username;
        this.remark =remark;
        this.code = code;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        dets = new ArrayList<Map<String,Object>>();

        SignMaker sm = new SignMaker();
        String sign1 = "";
        if (state==null){

            String sign = sm.getsign("type="+type,"id="+id);
            sign1=sign;
            PostForInputstream pfi = new PostForInputstream();
            InputStream is2 =pfi.getStream2(type,id,state,sign1,username,remark,code);


            //XmlInputStream xmlInputStream = new XmlInputStream();
            //InputStream is = xmlInputStream.getStream(type,id,state,sign1,username,remark,code);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            DetailPraser dp = new PullPraserDetail();
            try {
                mdetail = dp.parse(is2);
                for (Detail detail:mdetail){
                    Map<String, Object> map = new HashMap<String, Object>();
                    if (detail.getName()==null){
                        map.put("name","---");
                    }else{
                        map.put("name",detail.getName());
                    }
                    if (detail.getAddress()==null){
                        map.put("address","---");
                    }else {
                        map.put("address",detail.getAddress());
                    }
                    if (detail.getPhone()==null){
                        map.put("phone","---");
                    }else {

                        map.put("phone",detail.getPhone());
                    }if (detail.getPrice()==null){
                        map.put("privce","---");
                    }else {

                        double money=Double.parseDouble(detail.getPrice());
                        String money2 =formatDouble4(money);

                        map.put("privce",money2);


                        //map.put("privce",detail.getPrice());
                    }if (detail.getGoodname()==null){
                        map.put("name1","---");
                    }else {
                        map.put("name1",detail.getGoodname());
                    }
                    if (detail.getGoodsmoeny()==null){
                        map.put("goodsmoney","---");
                    }else {

                        double money=Double.parseDouble(detail.getGoodsmoeny());
                      String money2 =formatDouble4(money);

                        map.put("goodsmoney",money2);
                    }if (detail.getCount()==null){
                        map.put("quantity","---");

                    }else {
                        map.put("quantity",detail.getCount());

                    }
                    if (detail.getAznumber()==null){
                        map.put("aznumber","---");
                    }else {
                        map.put("aznumber",detail.getAznumber());

                    }
                    if (detail.getAzreservation()==null){
                        map.put("azreservation","---");
                    }else {

                        map.put("azreservation",detail.getAzreservation().substring(0,10));
                    }
                    if (detail.getDelivery()==null){
                        map.put("delivery","---");
                    }else {

                        map.put("delivery",detail.getDelivery());
                    }
                    if (detail.getServerType()==null){
                        map.put("servertype","---");
                    }else {

                        map.put("servertype",detail.getServerType());
                    }
                    if (detail.getServicestype()==null){
                        map.put("servicestype","---");
                    }else {

                        map.put("servicestype",detail.getServicestype());
                    }
                    if (detail.getPilot()==null){
                        map.put("pilot","---");
                    }else {

                        map.put("pilot",detail.getPilot());
                    }

                    if (detail.getPilotphone()==null){
                        map.put("pilotphone","---");
                    }else {

                        map.put("pilotphone",detail.getPilotphone());
                    }


                    dets.add(map);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return dets;

        }if(remark==null&&state!=null&&code==null) {
            String sign = sm.getsign("type="+type,"id="+id,"state="+state);
            sign1=sign;

            PostForInputstream pfi = new PostForInputstream();
            InputStream is2 =pfi.getStream2(type,id,state,sign1,username,remark,code);

            //XmlInputStream xmlInputStream = new XmlInputStream();
            //InputStream is = xmlInputStream.getStream(type,id,state,sign1,username,remark,code);

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
        if (remark!=null&&code==null){
            String sign = sm.getsign("type="+type,"id="+id,"state="+state,"username="+username,"remark="+remark);
            sign1=sign;

            PostForInputstream pfi = new PostForInputstream();
            InputStream is2 =pfi.getStream2(type,id,state,sign1,username,remark,code);

            //XmlInputStream xmlInputStream = new XmlInputStream();
            //InputStream is = xmlInputStream.getStream(type,id,state,sign1,username,remark,code);

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
        }if (remark!=null&&code!=null&&state!=null){

            Log.i("xml","codecodecodecode");
            String sign = sm.getsignCode("type="+type,"id="+id,"state="+state,"code="+code,"remark="+remark);
            sign1=sign;
            PostForInputstream pfi = new PostForInputstream();
            InputStream is2 =pfi.getStream2(type,id,state,sign1,username,remark,code);

            //XmlInputStream xmlInputStream = new XmlInputStream();
            //InputStream is = xmlInputStream.getStream(type,id,state,sign1,username,remark,code);

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
        return null;
    }
    public void setOnDataFinishedListener(
            OnDataFinishedListener onDataFinishedListener) {
        this.onDataFinishedListener = onDataFinishedListener;
    }
    public static String formatDouble4(double d) {
        DecimalFormat df = new DecimalFormat("#.00");


        return df.format(d);
    }

    @Override
    protected void onPostExecute(Object o) {
        Log.i("xml","o======="+o.toString());
        Log.i("xml","o.getClass().getName()"+o.getClass().getName());
        if (o.getClass().getName().equals("java.util.ArrayList")){
            List<Map<String, Object>> result = (List<Map<String, Object>>) o;
            Log.i("xml","result======="+result.size());

            if(result.size()==0){
                onDataFinishedListener.onDataFailed();

            }if (result.size()!=0){
                onDataFinishedListener.onDataSuccessfully(result);
            }
        }else {

            String result = (String) o;
            if (result.equals("true")){
                onDataFinishedListener.onDataSuccessfully(result);

            }else {
                onDataFinishedListener.onDataFailed();

            }



        }

    }
}
