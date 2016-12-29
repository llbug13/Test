package com.ll.test.java;

import android.graphics.Path;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;

import com.ll.test.R;
import com.ll.test.app.app;
import com.ll.test.log.L;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.RandomAccess;
import java.util.Scanner;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.logging.FileHandler;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by ll on 16-12-20.
 */

public class Test_io {
    //    Reader;
//    InputStream;
//    OutputStream;
//    Writer;
//    String;
    public static String showUserInfo() {
//        System.out.println(System.getProperty("java.library.path"));
//        System.out.println(File.separator);
        InputStream is = null;
        String ret = null;
        try {
            is = app.getContext().getResources().openRawResource(R.raw.ll1);
            int len = is.available();
            byte[] buffer = new byte[len];
            is.read(buffer);
            ret = new String(buffer);
            System.out.println(Charset.defaultCharset().name() + len + ret);
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            if (is == null) {
                try {
                    is.close();
                } catch (IOException e) {
//                    Log.e(tag, "close file", e);
                }
            }
        }
        return ret;
    }

    {
//        File sdcard = Environment.getExternalStorageDirectory();
        //        Map<String, Charset> m = Charset.availableCharsets();
//        for (Map.Entry<String, Charset> s1 : m.entrySet()) {
//            Log.e("kkk", s1.getKey() + ":" + s1.getValue());
//        }
//        str();
        //        getDevMountList();
//        java_test_text.setText(getDatabasePath("llll").toString());
//        try {
//            FileInputStream f = openFileInput(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shumei");
////            int len = f.available();
////            java_test_text.setText(len);
////            byte[] buffer = new byte[len];
////            f.read(buffer);
////            java_test_text.setText(new String(buffer));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String filename = "shumei";
//        String string = "Hello world!";
//        FileOutputStream outputStream;
//        try{
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(string.getBytes());
//            outputStream.close();
//        } catch(Exception e) {
//            e.printStackTrace();
//        }


//        File sdCardDir = new File("/mnt/sdcard"); //获取SDCard目录
//
//        File saveFile = new File(sdCardDir, "itcast.txt");
//
////上面两句代码可以合成一句： File saveFile = new File("/mnt/sdcard/itcast.txt");
//
//        FileOutputStream outStream = new FileOutputStream(saveFile);
//
//        outStream.write("传智播客test".getBytes());


//        openFileInput("");
//        /data/datea<package name>/files
//        ①MODE_PRIVATE(该文件只能被当前应用程序读写)
//        ②MODE_APPEND(以追加方式打开，可以在文件中追加内容)
//        ③MODE_WORLD_READABLE(该文件内容可以被其他应用程序读取)
//        ④MODE_WORLD_WRITEABLE(该文件内容可以被其他应用程序度，写)
    }

    //    文件操作
    public static void fileTest() {
//        路径
//        System.out.println(System.getProperty("base.dir"));
//        Path path = Path
        File file = null;
//      文件zhuihou shijian
//        Date date = new Date(file.lastModified());
        SecurityManager securityManage = System.getSecurityManager();
//        jianchawenjian shi fou neng du qu
        System.out.printf("%f %e %e", 213.123d, 1231.13d, 123123.4);
//        securityManage.checkRead();
//        file.lastModified();
//        file.delete();

        
    }

    //    string编码解码
    private void str() {
        String s = "黎磊";
        Charset charset = Charset.defaultCharset();
        ByteBuffer b = charset.encode(s);
        byte[] bytes = b.array();
        byte[] bytes1 = s.getBytes();
        for (byte b1 : bytes) {
            char c = (char) b1;
        }

        int i = 0;
        int i1 = bytes1.length < bytes.length ? bytes1.length : bytes.length;
        Log.e("jjjjjj", bytes.length + ":" + bytes1.length);
        while (i < i1) {
            Log.e("jjjjjj", bytes[i] + ":" + (char) bytes[i] + ":" + bytes1[i]);
            i++;
        }
        Log.e("jjjjjj", "" + bytes[6]);
        Log.e("jjjjjj", "" + bytes[7]);

        ByteBuffer bbuf = ByteBuffer.wrap(bytes);
        CharBuffer charBuffer = charset.decode(bbuf);
        Log.e("jjjj", charBuffer.toString());
        Log.e("jjjj", Base64.encodeToString(s.getBytes(), 0));


    }

    //    序列化
    public static void objectIo() {
        ObjectInputStream object = null;
        ObjectOutputStream out = null;
        try {
//            object = new ObjectInputStream(new ByteArrayInputStream("�� \u0005t \u0003asd".getBytes()));

            out = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/ll.dat"));
//            out.writeObject();
//            out = new ObjectOutputStream(System.out);
            String s = "ll";
            System.out.println(s);
            out.writeObject(s);
//            out.writeObject("llll");
//            s = (String) object.readObject();
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        finally {
            if (object != null) {
                try {
                    object.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    //    zip操作
    public static void zip() {
        ZipInputStream zip = null;
        System.out.println(System.getProperty("user.dir"));
        try {
            zip = new ZipInputStream(new FileInputStream(System.getProperty("user.dir") + "/" + "Thinking.zip"));
            ZipEntry entry;
            System.out.println(zip.available());
            while ((entry = zip.getNextEntry()) != null) {
                System.out.println(zip.available() + ":" + entry.getName());

                if (entry.getName().equals("Thinking in Java/about/index.htm")) {
                    Scanner in = new Scanner(zip, "gbk");
                    while (in.hasNextLine()) {
//                    比如读取zip中的text
                        System.out.println(in.nextLine());
                    }
                }

                zip.closeEntry();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (IOException e) {
//                    e.printStackTrace();
                }
            }
        }
        ZipOutputStream zipo = null;
        try {
            zipo = new ZipOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/" + "ll.zip"));
            zipo.setLevel(Deflater.DEFAULT_COMPRESSION);
//            zipo.setMethod(ZipOutputStream.DEFLATED);
            zipo.setMethod(ZipOutputStream.STORED);
            ZipEntry zipEntry = new ZipEntry("ll.text");
//            是否时目录
            zipEntry.isDirectory();
//            得到CRC32
            zipEntry.getCrc();
//            得到未压缩时的大小，-1表示不知
            zipEntry.getSize();
//            ZipFile zipFile=new ZipFile("");

            zipo.putNextEntry(zipEntry);
//            向ll.text中写入数据
            zipo.write("sdasd".getBytes());
            zip.closeEntry();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipo != null) {
                try {
                    zipo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    //    2进制的操作
    public static void data() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(System.out);
            dataOutputStream.write(50);
            char c = '中';
            long c1 = c;
            System.out.println((c1 >> 8) + ":" + (int) c);
            byte[] bytes = new byte[]{50};
            dataOutputStream.write(bytes);
            dataOutputStream.write("中".getBytes());
            RandomAccessFile file = new RandomAccessFile("", "");
            file.seek(4);
            file.getFilePointer();
//            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(dataOutputStream);
//            outputStreamWriter.w
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public File getDatabasePath(String name) {
        File sdcard = Environment.getExternalStorageDirectory();
        String dbfile = sdcard.getAbsolutePath() + File.separator + "ll" + File.separator + name;

        if (!dbfile.endsWith(".db")) {
            dbfile += ".db";
        }
        File result = new File(dbfile);
//        if (!result.getParentFile().exists()) {
//            result.getParentFile().mkdirs();
//        }
        if (!result.exists()) {
            result.mkdirs();
        }

        return result;
    }
//    public static class CreateFiles {
//
//        static String filenameTemp = IDNA.Info. + "/hhaudio" + ".txt";
//
//        //创建文件夹及文件
//        public void CreateText() throws IOException {
//            File file = new File(IDNA.Info.audioPath);
//            if (!file.exists()) {
//                try {
//                    //按照指定的路径创建文件夹
//                    file.mkdirs();
//                } catch (Exception e) {
//                    // TODO: handle exception
//                }
//            }
//            File dir = new File(filenameTemp);
//            if (!dir.exists()) {
//                try {
//                    //在指定的文件夹中创建文件
//                    dir.createNewFile();
//                } catch (Exception e) {
//                }
//            }
//
//        }
//
//        //向已创建的文件中写入数据
//        public void print(String str) {
//            FileWriter fw = null;
//            BufferedWriter bw = null;
//            String datetime = "";
//            try {
//                SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " "
//                        + "hh:mm:ss");
//                datetime = tempDate.format(new java.util.Date()).toString();
//                fw = new FileWriter(filenameTemp, true);//
//                // 创建FileWriter对象，用来写入字符流
//                bw = new BufferedWriter(fw); // 将缓冲对文件的输出
//                String myreadline = datetime + "[]" + str;
//
//                bw.write(myreadline + "\n"); // 写入文件
//                bw.newLine();
//                bw.flush(); // 刷新该流的缓冲
//                bw.close();
//                fw.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                try {
//                    bw.close();
//                    fw.close();
//                } catch (IOException e1) {
//                    // TODO Auto-generated catch block
//                }
//            }
//        }
//    }

    /**
     * 获取内置SD卡路径
     *
     * @return
     */
    public String getInnerSDCardPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }

    /**
     * 获取外置SD卡路径
     *
     * @return 应该就一条记录或空
     */
    public List getExtSDCardPath() {
        List lResult = new ArrayList();
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                Log.e("llll", line);
                if (line.contains("extSdCard")) {
                    String[] arr = line.split(" ");
                    String path = arr[1];
                    File file = new File(path);
                    if (file.isDirectory()) {
                        Log.e("llllllllll", line);
                        lResult.add(path);
                    }
                }
            }
            isr.close();
        } catch (Exception e) {
        }
        return lResult;
    }

    public String getPath2() {
        String sdcard_path = null;
        String sd_default = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        Log.d("text", sd_default);
        if (sd_default.endsWith("/")) {
            sd_default = sd_default.substring(0, sd_default.length() - 1);
        }
        // 得到路径
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String line;
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line.contains("secure"))
                    continue;
                if (line.contains("asec"))
                    continue;
                if (line.contains("fat") && line.contains("/mnt/")) {
                    String columns[] = line.split(" ");
                    if (columns != null && columns.length > 1) {
                        if (sd_default.trim().equals(columns[1].trim())) {
                            continue;
                        }
                        sdcard_path = columns[1];
                    }
                } else if (line.contains("fuse") && line.contains("/mnt/")) {
                    String columns[] = line.split(" ");
                    if (columns != null && columns.length > 1) {
                        if (sd_default.trim().equals(columns[1].trim())) {
                            continue;
                        }
                        sdcard_path = columns[1];
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Log.d("text", sdcard_path);
        return sdcard_path;
    }
//    Environment.getExternalStoragePublicDirectory(String type)函数，该函数可以返回特定类型的目录，目前支持如下类型：
//    DIRECTORY_ALARMS //警报的铃声
//            DIRECTORY_DCIM //相机拍摄的图片和视频保存的位置
//    DIRECTORY_DOWNLOADS //下载文件保存的位置
//            DIRECTORY_MOVIES //电影保存的位置， 比如 通过google play下载的电影
//    DIRECTORY_MUSIC //音乐保存的位置
//            DIRECTORY_NOTIFICATIONS //通知音保存的位置
//    DIRECTORY_PICTURES //下载的图片保存的位置
//            DIRECTORY_PODCASTS //用于保存podcast(博客)的音频文件
//    DIRECTORY_RINGTONES //保存铃声的位置
//
//    顺带描述怎么取得sdcard的空间大小，
//    File sdcardDir = Environment.getExternalStorageDirectory();
//    StatFs sf = new StatFs(sdcardDir.getPath()); //sdcardDir.getPath())值为/mnt/sdcard，想取外置sd卡大小的话，直接代入/mnt/sdcard2
//    long blockSize = sf.getBlockSize(); //总大小
//    long blockCount = sf.getBlockCount();
//    long availCount = sf.getAvailableBlocks(); //有效大小

    /**
     * 遍历 "system/etc/vold.fstab” 文件，获取全部的Android的挂载点信息
     *
     * @return
     */
    private static List<String> getDevMountList() {

        List<String> toSearch = FileUtils.readFile("/system/etc/vold.fstab");//.split(" ");
        Log.e("llllllkkkkkkkkk", System.getenv("EXTERNAL_STORAGE") + ":" + System.getenv("SECONDARY_STORAGE") + ":" + toSearch.size());
        ArrayList<String> out = new ArrayList<String>();
        for (int i = 0; i < toSearch.size(); i++) {
            if (toSearch.get(i).contains("dev_mount")) {
                if (new File(toSearch.get(i + 2)).exists()) {
                    Log.e("llllllkkkkkkkkk", toSearch.get(i + 2));
                    out.add(toSearch.get(i + 2));
                }
            }
        }
        return out;
    }

    private static class FileUtils {

        public static List<String> readFile(String path) {
            List<String> s = new ArrayList<>();
            try {
                Log.e("llllll", "aa");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
                String s1 = null;
                Log.e("llllll", s1);
                while ((s1 = bufferedReader.readLine()) != null) {
                    s.add(s1);
                    Log.e("llllll", s1);
                }
            } catch (FileNotFoundException e) {
                Log.e("llllllkkkkkkkkk", "adasd");
                e.printStackTrace();
            } catch (IOException e) {
                Log.e("llllllkkkkkkkkk", "ladas");
                e.printStackTrace();
            }
            return s;
        }
    }
//    通过Environment获取的
//
//    Environment.getDataDirectory().getPath() :                          获得根目录/data 内部存储路径
//
//    Environment.getDownloadCacheDirectory().getPath()  :      获得缓存目录/cache
//
//    Environment.getExternalStorageDirectory().getPath():         获得SD卡目录/mnt/sdcard（获取的是手机外置sd卡的路径）
//
//            Environment.getRootDirectory().getPath() :                          获得系统目录/system
//
//
//            通过Context获取的
//
//    Context.getDatabasePath()                                                   返回通过Context.openOrCreateDatabase 创建的数据库文件
//
//    Context.getCacheDir().getPath() :                                          用于获取APP的cache目录 /data/data/<application package>/cache目录
//
//    Context.getExternalCacheDir().getPath()  :                            用于获取APP的在SD卡中的cache目录/mnt/sdcard/Android/data/<application package>/cache
//
//    Context.getFilesDir().getPath()  :                                           用于获取APP的files目录 /data/data/<application package>/files
//
//    Context.getObbDir().getPath():                                             用于获取APPSDK中的obb目录 /mnt/sdcard/Android/obb/<application package>
//
//            Context.getPackageName() :                                                用于获取APP的所在包目录
//
//    Context.getPackageCodePath()  :                                         来获得当前应用程序对应的 apk 文件的路径
//
//    Context.getPackageResourcePath() :                                    获取该程序的安装包路径


}
