package com.ll.test.wifi;

/**
 * Created by LL on 2016/9/17.
 */

import android.net.wifi.WifiInfo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//        1、通过Runtime.getRuntime().exec("su")获取root权限。
//        2、通过process.getOutputStream()和process.getInputStream()获取终端的输入流和输出流。
//        3、通过dataOutputStream.writeBytes("cat /data/misc/wifi/*.conf\n")往终端中输入命令。注意，这里必须要有\n作为换行，否则会与后一个exit命令作为一个命令，最终导致命令执行失败，无法得到结果。
//        4、通过dataInputStream获取命令执行结果，并以UTF-8的编码转换成字符串。
//        5、使用正则表达式过滤出wifi的用户名和密码。

public class WifiManage {

    public static Map<String, String> Read() throws Exception {
        List<WifiInfo> wifiInfos = new ArrayList<WifiInfo>();
        Process process = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        StringBuffer wifiConf = new StringBuffer();
        try {
            process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            dataInputStream = new DataInputStream(process.getInputStream());
            dataOutputStream
                    .writeBytes("cat /data/misc/wifi/*.conf\n");
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    dataInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                wifiConf.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            process.waitFor();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                process.destroy();
            } catch (Exception e) {
                throw e;
            }
        }


        Pattern network = Pattern.compile("network=\\{([^\\}]+)\\}", Pattern.DOTALL);
        Matcher networkMatcher = network.matcher(wifiConf.toString());
        Map<String, String> map = new HashMap<>();
        while (networkMatcher.find()) {
            String networkBlock = networkMatcher.group();
            Pattern ssid = Pattern.compile("ssid=\"([^\"]+)\"");
            Matcher ssidMatcher = ssid.matcher(networkBlock);
            if (ssidMatcher.find()) {
                String ss = ssidMatcher.group(1);
                Pattern psk = Pattern.compile("psk=\"([^\"]+)\"");
                Matcher pskMatcher = psk.matcher(networkBlock);
                if (pskMatcher.find()) {
                    map.put(ss, pskMatcher.group(1));
                } else {
                    map.put(ss, "无密码");
                }
            }

        }

        return map;
    }

}