package com.wgz.ant.antinstall.xmlpraser;

import android.util.Xml;

import com.wgz.ant.antinstall.bean.Worker;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qwerr on 2015/12/23.
 */
public class PullPraserWorker implements WorkerParser {
    @Override
    public List<Worker> parse(InputStream is) throws Exception {
        List<Worker> snews = null;
        Worker workers = null;
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
                    snews = new ArrayList<Worker>();  //初始化Goods集合
                    break;
                // 判断当前事件是否为标签元素开始事件
                case XmlPullParser.START_TAG:
                    if (pullParser.getName().equals("Table")) {  //判断开始标签元素
                        workers = new Worker();
                    }

                    else if (pullParser.getName().equals("username")) {
                        event=pullParser.next();//让解析器指向id属性的值

                        workers.setWorkername(pullParser.getText());

                    }
                    else if (pullParser.getName().equals("userid")) {
                        event=pullParser.next();
                        workers.setWorkerID(pullParser.getText());
                    }
                    else if (pullParser.getName().equals("id")) {
                        event=pullParser.next();
                        workers.setWorkNum(pullParser.getText());
                    }

                    break;
                // 判断当前事件是否为标签元素结束事件
                case XmlPullParser.END_TAG:
                    if (pullParser.getName().equals("Table")) {  // 判断结束标签元素
                        snews.add(workers);
                        workers = null;
                    }
                    break;

            }
            // 进入下一个元素并触发相应事件
            event = pullParser.next();
        }

        return snews;
    }
}
