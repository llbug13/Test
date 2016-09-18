package com.ll.test.wifi;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.provider.Settings;

import com.ll.test.R;
import com.ll.test.base.BActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by LL on 2016/9/16.
 */
public class WifiTestActivity extends BActivity {
    private BluetoothAdapter bluetoothAdapter;
    //    打开蓝牙
    private static final int BLUETOOTH = 1;
    //    打开蓝牙可别扫描
    private static final int DISCOVERY_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
//        bluetoothAdapter=null表示本机没有蓝牙设备
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        wifi();
        startServerSocket();
    }

    private boolean isOpenBuletooth() {
//        需要权限<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
//        直接打开
//        bluetoothAdapter.enable();
//        直接关闭
//        bluetoothAdapter.disable();
//        设备是否是打开
        if (bluetoothAdapter.isEnabled()) {
//            本地
            String address = bluetoothAdapter.getAddress();
            String name = bluetoothAdapter.getName();
            i(address + "::lll" + name);
//需要权限
            if (bluetoothAdapter.setName("ll")) {
                toast("改名成功");
            }
            return true;
        } else {
//            打开
            openBuletooth();
        }
        i("::");
        return false;
    }

    private void stateBuletooth() {
//        蓝牙关闭 : int STATE_OFF , 值为10, 蓝牙模块处于关闭状态;
//        蓝牙打开中 : int STATE_TURNING_ON , 值为11, 蓝牙模块正在打开;
//        蓝牙开启 : int STATE_ON , 值为12, 蓝牙模块处于开启状态;
//        蓝牙开启中 : int STATE_TURNING_OFF , 值为13, 蓝牙模块正在关闭;
        switch (bluetoothAdapter.getState()) {
            case BluetoothAdapter.STATE_TURNING_ON:
                break;
            case BluetoothAdapter.STATE_ON:
                break;
            case BluetoothAdapter.STATE_TURNING_OFF:
                break;
            case BluetoothAdapter.STATE_OFF:
                break;
        }
    }

    //    能否被扫描的状态
    private void stateScanBuletooth() {
//        无功能状态 : int SCAN_MODE_NONE , 值为20, 查询扫描和页面扫描都失效, 该状态下蓝牙模块既不能扫描其它设备, 也不可见;
//        扫描状态 : int SCAN_MODE_CONNECTABLE , 值为21, 查询扫描失效, 页面扫描有效, 该状态下蓝牙模块可以扫描其它设备,
// 从可见性来说只对已配对的蓝牙设备可见, 只有配对的设备才能主动连接本设备;
//        可见状态 : int SCAN_MODE_CONNECTABLE_DISCOVERABLE, 值为23, 查询扫描和页面扫描都有效;
        switch (bluetoothAdapter.getScanMode()) {
            case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
                break;
            case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
                break;
            case BluetoothAdapter.SCAN_MODE_NONE:
                break;
        }
    }

    private void makeDiscoverable() {
        /**
         * 蓝牙可别扫描，启用可发现机制
         * EXTRA_DISCOVERABLE_DURATION设置秒数
         * 默认是120秒
         */
        startActivityForResult(
                new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE).putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 200),
                DISCOVERY_REQUEST);
    }

    private void openBuletooth() {
//        开蓝牙
        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), BLUETOOTH);
    }

    //蓝牙扫描
    private void discoveryBuletooth() {
//        注册监听
        registerReceiver(discoverMonitor, new IntentFilter(dstarted));
        registerReceiver(discoverMonitor, new IntentFilter(dfinished));
//        扫描时会影响蓝牙的通信性能
        if (bluetoothAdapter.isDiscovering()) {
//            取消扫描
            bluetoothAdapter.cancelDiscovery();
        } else {
//            扫描
            bluetoothAdapter.startDiscovery();
        }

    }

    String dstarted = BluetoothAdapter.ACTION_DISCOVERY_STARTED;
    String dfinished = BluetoothAdapter.ACTION_DISCOVERY_FINISHED;
    //    扫描的结果是异步的，BroadcastReceiver接收
    private BroadcastReceiver discoverMonitor = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
//                    启动发现过程
                    break;
                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
//                    发现完成
                    break;
            }
        }
    };
    //      发现蓝牙设备
    private ArrayList<BluetoothDevice> deviceList =
            new ArrayList<BluetoothDevice>();

    private void startDiscovery() {
        registerReceiver(discoveryResult,
                new IntentFilter(BluetoothDevice.ACTION_FOUND));

        if (bluetoothAdapter.isEnabled() && !bluetoothAdapter.isDiscovering()) {
//            在没有扫描的时候查找现在已扫描的内容
            deviceList.clear();
        }
        bluetoothAdapter.startDiscovery();
    }

    //    蓝牙连接
    private BluetoothSocket transferSocket;

    // 作为服务器
    private UUID startServerSocket() {
        UUID uuid = UUID.fromString("a60f35f0-b93a-11de-8a39-08002009c666");
        String name = "bluetoothserver";
        try {
            i("bluetoothserver Socket listener IO Exception");
            final BluetoothServerSocket btserver =
                    bluetoothAdapter.listenUsingRfcommWithServiceRecord(name, uuid);

            Thread acceptThread = new Thread(new Runnable() {
                public void run() {
                    try {
                        i("bluetoothserver Server connection IO Exception");
                        // Block until client connection established.
                        BluetoothSocket serverSocket = btserver.accept();
                        i("111111111111bluetoothserver Server connection IO Exception");
                        // Start listening for messages.
                        StringBuilder incoming = new StringBuilder();
                        listenForMessages(serverSocket, incoming);
                        // Add a reference to the socket used to send messages.
                        transferSocket = serverSocket;
                    } catch (IOException e) {
                        i("Server connection IO Exception");
                    }
                }
            });
            acceptThread.start();
        } catch (IOException e) {
            i("Socket listener IO Exception");
        }
        return uuid;
    }

    private boolean listening = false;

    private void listenForMessages(BluetoothSocket socket,
                                   StringBuilder incoming) {
        i("1024Socket listener IO Exception");
        listening = true;
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        try {
            InputStream instream = socket.getInputStream();
            int bytesRead = -1;

            while (listening) {
                bytesRead = instream.read(buffer);
                if (bytesRead != -1) {
                    String result = "";
                    while ((bytesRead == bufferSize) && (buffer[bufferSize - 1] != 0)) {
                        result = result + new String(buffer, 0, bytesRead - 1);
                        bytesRead = instream.read(buffer);
                    }
                    result = result + new String(buffer, 0, bytesRead - 1);
                    incoming.append(result);
                }
//                关闭连接
                socket.close();
            }
        } catch (IOException e) {
            i("Message received failed.");
        } finally {
        }
    }

    //  发送
    private void sendMessage(BluetoothSocket socket, String message) {
        OutputStream outStream;
        try {
            outStream = socket.getOutputStream();

            // Add a stop character.
            byte[] byteArray = (message + " ").getBytes();
            byteArray[byteArray.length - 1] = 0;
            outStream.write(byteArray);
        } catch (IOException e) {
            i("Message send failed.");
        }
    }

    //    得到设备
    private void getBluetoothDevice() {
        //（1）得到所有已经配对的蓝牙适配器对象
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        if (devices.size() > 0) {
            //用迭代
            for (Iterator iterator = devices.iterator(); iterator.hasNext(); ) {
                //得到BluetoothDevice对象,也就是说得到配对的蓝牙适配器
                BluetoothDevice device = (BluetoothDevice) iterator.next();
                i(device.getName());
                //得到远程蓝牙设备的地址
            }
        }
//        得到指定的设备
        BluetoothDevice device = bluetoothAdapter.getRemoteDevice("12:31:13:13:13:13");
//        还可使用上面的搜索
    }

    private void connectToServerSocket(BluetoothDevice device, UUID uuid) {
//        UUID是匹配吗
        try {
            BluetoothSocket clientSocket
                    = device.createRfcommSocketToServiceRecord(uuid);
//            阻尼的，如果远端设备接收了该连接，他们将在通信过程中共享RFFCOMM信道，并且connect返回。
            // Block until server connection accepted.
//            连接之后两者没有明显的区别（客户和服务器）
            clientSocket.connect();

            // Start listening for messages.
            StringBuilder incoming = new StringBuilder();
//            开始监听
            listenForMessages(clientSocket, incoming);

            // Add a reference to the socket usedi to send messages.
            transferSocket = clientSocket;

        } catch (IOException e) {
            i("Blueooth client I/O Exception");
        }
    }


    BroadcastReceiver discoveryResult = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String remoteDeviceName =
                    intent.getStringExtra(BluetoothDevice.EXTRA_NAME);

            BluetoothDevice remoteDevice =
                    intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            i(remoteDevice.getName());
            deviceList.add(remoteDevice);

//            Log.d(TAG, "Discovered " + remoteDeviceName);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BLUETOOTH) {
            if (resultCode == RESULT_OK) {
//                打开成功
            }
        }
        if (requestCode == DISCOVERY_REQUEST) {
            if (resultCode == RESULT_CANCELED) {
//                可被扫描被拒绝
            }
        }
    }

    private BluetoothstateBroadcastReceiver bluetoothstateBroadcastReceiver;

    private void initBBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
//        状态
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
//        被扫描状态
        intentFilter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        if (bluetoothstateBroadcastReceiver == null) {
            bluetoothstateBroadcastReceiver = new BluetoothstateBroadcastReceiver();
        }
        registerReceiver(bluetoothstateBroadcastReceiver, intentFilter);
    }

    class BluetoothstateBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//             过去状态
            String prevStateExtra = BluetoothAdapter.EXTRA_PREVIOUS_STATE;
//             当前状态
            String stateExtra = BluetoothAdapter.EXTRA_STATE;
            //             可被扫描过去状态
            String prevStateScanExtra = BluetoothAdapter.EXTRA_PREVIOUS_SCAN_MODE;
//             当前状态
            String stateScanExtra = BluetoothAdapter.EXTRA_SCAN_MODE;
            int state = intent.getIntExtra(stateExtra, -1);
            int previousState = intent.getIntExtra(prevStateExtra, -1);
            if (intent.getAction() == BluetoothAdapter.ACTION_STATE_CHANGED) {
                switch (state) {
                    case BluetoothAdapter.STATE_TURNING_ON:
                        i("onReceive---------STATE_TURNING_ON");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        i("onReceive---------STATE_ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        i("onReceive---------STATE_TURNING_OFF");
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        i("onReceive---------STATE_OFF");
                        break;
                    default:
                        break;
                }
            }
        }
    }


    /**
     * 管理网络
     */

    private void network() {
//        监听网络的连接状态，设置首先网络，连接失败的转换
        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        得到后台数据传输
        boolean backgroudEnabled = connectivityManager.getBackgroundDataSetting();
//        注册后台数据改变监听
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
//                40。0后被弃用，并总是返回true
//                原先是应用级的
//                现在是系统级的，设置数据限制和后台限制
                boolean backgroundEnabled = connectivityManager.getBackgroundDataSetting();
            }
        }, new IntentFilter(ConnectivityManager.ACTION_BACKGROUND_DATA_SETTING_CHANGED));
//        获取网络信息
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        网络是否连接
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {

        }
//        是否是wifi
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {

        }
//        网络状态变化
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
//    没有连接true是表示没有一个活动的连接
                intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            }
        }, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        NetworkInfo.State mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        //wifi
        NetworkInfo.State wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        //如果3G网络和wifi网络都未连接，且不是处于正在连接状态 则进入Network Setting界面 由用户配置网络连接
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
            return;
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
            return;


        startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));//进入无线网络配置界面
        //startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)); //进入手机中的wifi网络设置界面

    }

    //    wifi
    private void wifi() {
        final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//        没有打开
        if (!wifiManager.isWifiEnabled()) {
            if (wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLING) {
//                设置wifi打开
                wifiManager.setWifiEnabled(true);
            }
        }

        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getBSSID() != null) {
//            强弱等级
            int strength = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), 5);
            int speed = wifiInfo.getLinkSpeed();
            String units = WifiInfo.LINK_SPEED_UNITS;
            String ssid = wifiInfo.getSSID();

            String cSummary = String.format("Connected to %s at %s%s. " +
                            "Strength %s/5",
                    ssid, speed, units, strength);
            i(cSummary);
        }


        // Register a broadcast receiver that listens for scan results.
//        扫描结果
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                List<ScanResult> results = wifiManager.getScanResults();
                ScanResult bestSignal = null;
                for (ScanResult result : results) {
                    if (bestSignal == null || WifiManager.compareSignalLevel(bestSignal.level, result.level) < 0)
//                        最强信号
                        bestSignal = result;
                }
                String connSummary = String.format("%s networks found. %s is" +
                                "the strongest.",
                        results.size(),
                        bestSignal.SSID);

                toast(connSummary);
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

        // Initiate a scan.
//        开始扫描
        wifiManager.startScan();

//        获取可用配置的一个列表
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for (WifiConfiguration wifiConfiguration : list) {
//            wifiConfiguration.BSSID;
//            wifiConfiguration.SSID;
//            唯一标示
//            wifiConfiguration.networkId;
//            优先级
//            wifiConfiguration.priority;
//            WifiConfiguration.Status.CURRENT中的一个
//            wifiConfiguration.status;
            i(wifiConfiguration.SSID);
        }
        if (list.size() > 0) {
            int netid = list.get(0).networkId;
//            用该网络
            wifiManager.enableNetwork(netid, true);
//            添加网络
//            wifiManager.addNetwork()
//            更改，网络ID和wifiConfiguration配置
//            wifiManager.updateNetwork()
//            wifiManager.removeNetwork()
//            最后保存
//            wifiManager.saveConfiguration();

        }
        try {
            Map<String, String> map = WifiManage.Read();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                i(entry.getKey() + "llllll" + entry.getValue());
            }
        } catch (Exception e) {
            i("llllll");
            e.printStackTrace();
        }


//        wifi通信 wi-fi direct传输数据
        final WifiP2pManager wifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
//        建立通信通道,Looper用于接收
        final WifiP2pManager.Channel channel = wifiP2pManager.initialize(this, getMainLooper(), new WifiP2pManager.ChannelListener() {
            @Override
            public void onChannelDisconnected() {
                i("jjjjjjjjjjjj");
                //                监听通道的丢失情况 wifi
            }
        });


//        wifi设置界面
//        Intent intent = new Intent(
//                android.provider.Settings.ACTION_WIRELESS_SETTINGS);
//        startActivity(intent);
//        发现对等设备的注册
        BroadcastReceiver peerDiscoveryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                wifiP2pManager.requestPeers(channel, new WifiP2pManager.PeerListListener() {
                    public void onPeersAvailable(WifiP2pDeviceList peers) {
                        deviceList1.clear();
                        deviceList1.addAll(peers.getDeviceList());
                        for (WifiP2pDevice wifiP2pDevice : deviceList1) {
                            i("jjjjjjjjj" + wifiP2pDevice.deviceName);
                        }
                    }
                });
            }
        };

        registerReceiver(peerDiscoveryReceiver, new IntentFilter(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION));
//        扫描监听,发现对等责备
        wifiP2pManager.discoverPeers(channel, actionListener);

//        连成功的广播
        BroadcastReceiver connectionChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // Extract the NetworkInfo
                String extraKey = WifiP2pManager.EXTRA_NETWORK_INFO;
                NetworkInfo networkInfo =
                        (NetworkInfo) intent.getParcelableExtra(extraKey);

                // Check if we're connected
//               是否建立连接
                if (networkInfo.isConnected()) {
                    wifiP2pManager.requestConnectionInfo(channel,
                            new WifiP2pManager.ConnectionInfoListener() {
                                public void onConnectionInfoAvailable(WifiP2pInfo info) {
                                    // If the connection is established
//                                    建立
                                    if (info.groupFormed) {
                                        // If we're the server
//                                        服务器
                                        if (info.isGroupOwner) {
                                            // TODO Initiate server socket.
                                            initiateServerSocket();
                                        }
                                        // If we're the client
//                                        客服
                                        else if (info.groupFormed) {
                                            // TODO Initiate client socket.
                                            initiateClientSocket(info.groupOwnerAddress.toString());
                                        }
                                    }
                                }
                            });
                } else {
                    i("Wi-Fi Direct Disconnected");
                }
            }
        };
        registerReceiver(connectionChangedReceiver, new IntentFilter(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION));
//        连接设备
        WifiP2pConfig config = new WifiP2pConfig();
        WifiP2pDevice device = null;
        config.deviceAddress = device.deviceAddress;
        wifiP2pManager.connect(channel, config, actionListener);
    }

    private void initiateServerSocket() {
        ServerSocket serverSocket;
        try {
            /**
             * Listing 16-25: Creating a Server Socket
             */
            serverSocket = new ServerSocket(8666);
//            阻尼
            Socket serverClient = serverSocket.accept();

            // TODO Start Sending Messages
        } catch (IOException e) {
            i("I/O Exception");
        }
    }

    private void initiateClientSocket(String hostAddress) {
        /**
         * Listing 16-26: Creating a client Socket
         */
        int timeout = 10000;
        int port = 8666;
        InetSocketAddress socketAddress
                = new InetSocketAddress(hostAddress, port);
        try {
            Socket socket = new Socket();
            socket.bind(null);
//            阻尼
            socket.connect(socketAddress, timeout);
        } catch (IOException e) {
            i("IO Exception.");
        }

        // TODO Start Receiving Messages
    }

    private List<WifiP2pDevice> deviceList1 = new ArrayList<WifiP2pDevice>();

    BroadcastReceiver p2pStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(
                    WifiP2pManager.EXTRA_WIFI_STATE,
                    WifiP2pManager.WIFI_P2P_STATE_DISABLED);

            switch (state) {
                case (WifiP2pManager.WIFI_P2P_STATE_ENABLED):
                    break;
                default:
            }
        }
    };
//    private void initializeWiFiDirect() {
//        wifiP2pManager =
//                (WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
//
//        wifiDirectChannel = wifiP2pManager.initialize(this, getMainLooper(),
//                new ChannelListener() {
//                    public void onChannelDisconnected() {
//                        initializeWiFiDirect();
//                    }
//                }
//        );
//    }

    //    监听是否成功，动作监听
    private WifiP2pManager.ActionListener actionListener = new WifiP2pManager.ActionListener() {
        public void onFailure(int reason) {
            String errorMessage = "WiFi Direct Failed: ";
            switch (reason) {
                case WifiP2pManager.BUSY:
                    errorMessage += "Framework busy.";
                    break;
                case WifiP2pManager.ERROR:
                    errorMessage += "Internal error.";
                    break;
                case WifiP2pManager.P2P_UNSUPPORTED:
                    errorMessage += "Unsupported.";
                    break;
                default:
                    errorMessage += "Unknown error.";
                    break;
            }
            i("jjjjjjjjjjjj" + errorMessage);
        }

        public void onSuccess() {
            // Success!
            // Return values will be returned using a Broadcast Intent
        }
    };

    /**
     * NFC
     */


    @Override
    protected void onResume() {
        super.onResume();
//        wifi变化监听
        registerReceiver(p2pStatusReceiver, new IntentFilter(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(p2pStatusReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (bluetoothstateBroadcastReceiver != null) {
            unregisterReceiver(bluetoothstateBroadcastReceiver);
        }
//        unregisterReceiver(discoveryResult);
        super.onDestroy();
    }
}



