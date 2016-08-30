package com.ll.test.internet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by LL on 2016/8/29.
 */
public class XMLPull {
    private void processStream(InputStream inputStream) {
//        创建xml pull分析器
        XmlPullParserFactory factory;
        try {
            factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
//            分配新的输入流
            xpp.setInput(inputStream, null);
            int eventType = xpp.getEventType();
//            继续直至到达文件末尾
            while (eventType == XmlPullParser.END_DOCUMENT) {
//                检查结果标记的开始标记。
                if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("result")) {
                    eventType = xpp.next();
                    String name = "";
                    while (!(eventType == XmlPullParser.END_TAG && xpp.getName().equals("result"))) {
//                        检查结果标记中的名称标记
                        if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
//                            提取poi的名称
                            name = xpp.nextText();
//                            移动到下一个标记
                            eventType = xpp.next();
                        }
                    }
                }
            }

        } catch (XmlPullParserException e) {

        } catch (IOException e) {

        }

    }
}
