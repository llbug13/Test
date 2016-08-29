package com.ll.test.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ll.test.R;

import java.util.List;

/**
 * Created by LL on 2016/8/25.
 */
public class PlugInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setIntent1(Intent newIntent) {
//        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        ActivityManager.RunningTaskInfo runningTaskInfo = manager.getRunningTasks(1).get(0);
        PackageManager manager = getPackageManager();
//        检查执行该数据的activity
        newIntent.setData(Uri.parse(""));
//        newIntent.addCategory(Intent.CATEGORY_ALTERNATIVE);
//        或者
        newIntent.addCategory(Intent.CATEGORY_SELECTED_ALTERNATIVE);
//        指定标识。本例中，返回默认category的filter
        int flags = PackageManager.MATCH_DEFAULT_ONLY;

        List<ResolveInfo> actions = manager.queryIntentActivities(newIntent, flags);
        Resources resources = getResources();
        for (ResolveInfo resolveInfo : actions) {
            resources.getString(resolveInfo.labelRes);
            ActivityInfo activityInfo = resolveInfo.activityInfo;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        动态的建立 按menu键
        Intent intent = new Intent();
        intent.setData(Uri.parse("http://"));
        intent.addCategory(Intent.CATEGORY_SELECTED_ALTERNATIVE);
        int menuGroup = 0;
        int menuItemId = 0;
        int menuItemOrder = Menu.NONE;
        ComponentName caller = getComponentName();
        Intent[] specificIntents = null;
        MenuItem[] outSpecificItems = null;
        int flags = Menu.FLAG_APPEND_TO_GROUP;
        menu.addIntentOptions(menuGroup,
                menuItemId,
                menuItemOrder,
                caller,
                specificIntents,
                intent,
                flags,
                outSpecificItems);
        return true;
    }
}
