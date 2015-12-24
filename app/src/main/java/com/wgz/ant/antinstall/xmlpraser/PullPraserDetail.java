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
        List<Detail> snews = null;
        Detail goods = null;
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
                    snews = new ArrayList<Detail>();  //初始化Goods集合
                    break;
                // 判断当前事件是否为标签元素开始事件
                case XmlPullParser.START_TAG:
                    if (pullParser.getName().equals("Table")) {  //判断开始标签元素
                        goods = new Detail();
                    }

                    else if (pullParser.getName().equals("name")) {
                        event=pullParser.next();//让解析器指向id属性的值

                        goods.setName(pullParser.getText());

                    }
                    else if (pullParser.getName().equals("goodsmoney")) {
                        event=pullParser.next();//让解析器指向pid属性的值
                        goods.setPrice(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("servicestyle")) {
                        event=pullParser.next();
                        goods.setType(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("quantity")) {
                        event=pullParser.next();
                        goods.setCount(pullParser.getText());
                    }

                    break;
                // 判断当前事件是否为标签元素结束事件
                case XmlPullParser.END_TAG:
                    if (pullParser.getName().equals("Table")) {  // 判断结束标签元素
                        snews.add(goods);
                        goods = null;
                    }
                    break;

            }
            // 进入下一个元素并触发相应事件
            event = pullParser.next();
        }

        return snews;
    }
}
