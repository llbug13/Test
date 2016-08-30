package com.ll.test.receiver;

import android.app.Activity;
import android.content.IntentFilter;

/**
 * Created by LL on 2016/8/25.
 */
public class RActivity extends Activity {

    private IntentFilter filter = new IntentFilter("com.ll.action.LL");
    private ReceiverTest receiverTest = new ReceiverTest();

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiverTest, filter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiverTest);
        super.onPause();
    }
}
