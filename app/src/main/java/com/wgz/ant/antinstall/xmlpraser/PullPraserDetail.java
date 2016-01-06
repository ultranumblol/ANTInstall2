package com.wgz.ant.antinstall.xmlpraser;

import android.util.Xml;

import com.wgz.ant.antinstall.bean.Detail;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwerr on 2015/12/23.
 */
public class PullPraserDetail implements DetailPraser {
    @Override
    public List<Detail> parse(InputStream is) throws Exception {
        List<Detail> details = null;
        Detail datails = null;
        // 由android.util.Xml创建一个XmlPullParser实例
        XmlPullParser pullParser = Xml.newPullParser();
        // 设置输入流 并指明编码方式
        pullParser.setInput(is, "UTF-8");
        // 产生第一个事件
        int event = pullParser.getEventType();
        while (event != XmlPullParser.END_DOCUMENT) {
            switch (event) {
                // 判断当前事件是否为文档开始事件
                case XmlPullParser.START_DOCUMENT:
                    details = new ArrayList<Detail>();  //初始化Goods集合
                    break;
                // 判断当前事件是否为标签元素开始事件
                case XmlPullParser.START_TAG:
                    if (pullParser.getName().equals("Table")) {  //判断开始标签元素
                        datails = new Detail();
                    }
                    else if (pullParser.getName().equals("aznumber")) {
                        event=pullParser.next();
                        datails.setAznumber(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("name")) {
                        event=pullParser.next();

                        datails.setName(pullParser.getText());

                    }
                    else if (pullParser.getName().equals("phone1")) {
                        event=pullParser.next();
                        datails.setPhone(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("address")) {
                        event=pullParser.next();
                        datails.setAddress(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("price")) {
                        event=pullParser.next();
                        datails.setPrice(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("name1")) {
                        event=pullParser.next();
                        datails.setGoodname(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("goodsmoney")) {
                        event=pullParser.next();
                        datails.setGoodsmoeny(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("quantity")) {
                        event=pullParser.next();
                        datails.setCount(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("delivery")) {
                        event=pullParser.next();
                        datails.setDelivery(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("azreservation")) {
                        event=pullParser.next();
                        datails.setAzreservation(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("servertype")) {
                        event=pullParser.next();
                        datails.setServerType(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("servicestype")) {
                        event=pullParser.next();
                        datails.setServicestype(pullParser.getText());
                    }


                    break;
                // 判断当前事件是否为标签元素结束事件
                case XmlPullParser.END_TAG:
                    if (pullParser.getName().equals("Table")) {  // 判断结束标签元素
                        details.add(datails);
                        datails = null;
                    }
                    break;

            }
            // 进入下一个元素并触发相应事件
            event = pullParser.next();
        }

        return details;
    }
}
