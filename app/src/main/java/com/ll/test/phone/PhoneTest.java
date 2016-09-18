package com.ll.test.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

import com.ll.test.R;
import com.ll.test.base.BActivity;

/**
 * Created by LL on 2016/9/18.
 */
public class PhoneTest extends BActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        PackageManager packageManager = getPackageManager();
        boolean tele = packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
        final boolean cdma = packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA);
        boolean gsm = packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_GSM);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int type = telephonyManager.getPhoneType();
        String typestr = "unknown";
        switch (type) {
            case (TelephonyManager.PHONE_TYPE_GSM):
                typestr = "GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_CDMA):
                typestr = "CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_SIP):
                typestr = "SIP";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                typestr = "NONE";
                break;
            default:
                break;
        }

        String deviceid = telephonyManager.getDeviceId();
        String softwareVersion = telephonyManager.getDeviceId();
        String phoneNumber = telephonyManager.getLine1Number();
//        连接网络的国家iso
        String iso = telephonyManager.getSimCountryIso();
//        连接网络的运营商 id(mac+mnc)
        String id = telephonyManager.getNetworkOperator();
//        连接网络的运营商
        String name = telephonyManager.getNetworkOperatorName();
//        连接网络的类型
        int networkName = telephonyManager.getNetworkType();
//        连接到一个移动网络时，并且在CDMA网络时这些指令不可靠
        i(typestr + ":::" + deviceid + ":::" + softwareVersion + ":::" + phoneNumber + ":::" + iso + ":::" + id + ":::" + name + ":::" + networkName);

//        当属于GSM的时候，一般都是SIM
//        确认状态，就绪状态
        int simstate = telephonyManager.getSimState();
        if (simstate == TelephonyManager.SIM_STATE_READY) {
//            获取SIM的ISO国家代码
            String simCountry = telephonyManager.getSimCountryIso();
//        sim运营商代码（MCC+MNC）
            String simid = telephonyManager.getSimOperator();
//        sim运营商的名称
            String simname = telephonyManager.getSimOperatorName();
//         sim的序列号
            String simnumber = telephonyManager.getSimSerialNumber();
            i(simstate + ":::" + simCountry + ":::" + simid + ":::" + simname + ":::" + simnumber);
        }
//        数据连接的状态
        int data = telephonyManager.getDataState();
//        数据传输activity
        int activity = telephonyManager.getDataActivity();
//        监听，使用掩码来设置监听的事件
        telephonyManager.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);
//                PhoneStateListener.LISTEN_CALL_STATE
                String callstate = "Unknown";
                switch (state) {
//                    不响，不同话时
                    case TelephonyManager.CALL_STATE_IDLE:
                        callstate = "idle";
                        break;
//                    正在通话
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        callstate = "OFFHOOK";
                        break;
//                    电话响铃
                    case TelephonyManager.CALL_STATE_RINGING:
                        callstate = "RINGING";
                        break;
                    default:
                        break;
                }
            }
        }, PhoneStateListener.LISTEN_NONE | PhoneStateListener.LISTEN_CALL_STATE);
//       蜂窝网位置监听
        PhoneStateListener clocation = new PhoneStateListener() {
            @Override
            public void onCellLocationChanged(CellLocation location) {
                super.onCellLocationChanged(location);
                if (location instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) location;
//                    蜂窝id
                    gsmCellLocation.getCid();
//                    区域代码
                    gsmCellLocation.getLac();
                } else if (location instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) location;
//                    基站id
                    cdmaCellLocation.getBaseStationId();
//                    基站纬度
                    cdmaCellLocation.getBaseStationLatitude();
//                    经度
                    cdmaCellLocation.getBaseStationLongitude();
                }
            }
        };
        telephonyManager.listen(clocation, PhoneStateListener.LISTEN_CELL_LOCATION);

//       服务变化
        PhoneStateListener service = new PhoneStateListener() {
            @Override
            public void onServiceStateChanged(ServiceState serviceState) {
                super.onServiceStateChanged(serviceState);
                switch (serviceState.getState()) {
                    case ServiceState.STATE_IN_SERVICE:
//                        正常
                        break;
                    case ServiceState.STATE_EMERGENCY_ONLY:
//                        紧急呼叫
                        break;
                    case ServiceState.STATE_OUT_OF_SERVICE:
//                        没有电话服务
                        break;
                    case ServiceState.STATE_POWER_OFF:
//                        一般是飞行模式，电话无线传输别关闭
                        break;
                }
//                是否是漫游
                serviceState.getRoaming();
//                serviceState.getOperator**();运营商信息
            }
        };
        telephonyManager.listen(service, PhoneStateListener.LISTEN_SERVICE_STATE);

//        移动数据变化
        PhoneStateListener datastate = new PhoneStateListener() {
            @Override
            public void onDataActivity(int direction) {
                super.onDataActivity(direction);
                switch (direction) {
                    case TelephonyManager.DATA_ACTIVITY_IN:
                        break;
                }
            }

            @Override
            public void onDataConnectionStateChanged(int state) {
                super.onDataConnectionStateChanged(state);
                switch (state) {
                    case TelephonyManager.DATA_CONNECTED:
                        break;
                }
            }
        };
        telephonyManager.listen(datastate, PhoneStateListener.LISTEN_DATA_ACTIVITY | PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
    }

    private BroadcastReceiver phone = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            TelephonyManager.ACTION_PHONE_STATE_CHANGED
//            电话状态变化
            String phonestate = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (phonestate.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
//                响铃状态
//                电话号码
                String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            }
        }
    };

    private void phone() {
//        ACTION_DIAL不需要权限
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123234"));
        startActivity(intent);
    }
}
