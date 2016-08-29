package com.ll.test.receiver;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by LL on 2016/8/29.
 */
public class ManifestReceiver {
    //    在运行时管理Manifest Receiver
//    使用Package Manager的setComponentEnabledSetting方法，可以在运行时启用和禁用应用程序的manifest Receiver。
// 可以使用这种技术来启用和禁用任何应用程序组件(包括Activity和Service)，但对于manifest Receiver尤其有用。
    private void manifest(Context context) {
        ComponentName myReceiverName = new ComponentName(context, ReceiverTest.class);
        PackageManager pm = context.getPackageManager();

//启用一个manifest receiver
        pm.setComponentEnabledSetting(myReceiverName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

//禁用一个manifest receiver
        pm.setComponentEnabledSetting(myReceiverName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

}
