package com.ll.test.internet;

import android.location.Location;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Entity;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by LL on 2016/8/29.
 */
public class Internet {
    //    网络测试
    private void useUrl() {
//        不要在主界面使用
        try {
            URL url = new URL("http://www.baidu.com");
            HttpURLConnection connection = (HttpURLConnection) url.getContent();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
//                分析
                Document document = db.parse(inputStream);
                Element element = document.getDocumentElement();
//得到每一项
                NodeList nl = document.getElementsByTagName("entry");
                for (int i = 0; i < nl.getLength(); i++) {
                    Element entry = (Element) nl.item(i);
                    Entity title = (Entity) element.getElementsByTagName("title").item(0);
                    String title1 = title.getFirstChild().getNodeValue();
                    Date a = new GregorianCalendar(0, 0, 0).getTime();
                    android.os.Handler handler = new android.os.Handler();
//                    定位
                    Location l = new Location("aa");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
//pass
                        }
                    });
                }

            }
        } catch (MalformedURLException e) {
//            e.printStackTrace();
        } catch (IOException e) {

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

    //api level 11后要开线程
    private void runUrl() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
//                开线程
                useUrl();
            }
        });
        t.start();
    }

}
