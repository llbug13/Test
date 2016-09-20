package com.ll.test;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.ll.test.activity.CycleActivity;
import com.ll.test.log.L;
import com.ll.test.view.CompassView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private QuickContactBadge badge;
    private CompassView l1;
    public static final String ACTION_REMOVE_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";
    public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        badge = (QuickContactBadge) findViewById(R.id.adge);
        l1 = (CompassView) findViewById(R.id.l1);
        l1.setOnClickListener(this);
        badge.assignContactFromPhone("123123131", false);
        addShortcut("lll");
//        badge.assignContactUri(Uri.parse("http://www.baidu.com"));
    }

    // 判读是否已经存在快捷方式
    public boolean isExistShortCut() {
        boolean isInstallShortcut = false;
        final ContentResolver cr = MainActivity.this.getContentResolver();
        // 本人的2.2系统是”com.android.launcher2.settings”,网上见其他的为"com.android.launcher.settings"
        final String AUTHORITY = "com.android.launcher2.settings";
        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI, new String[]{"title", "iconResource"}, "title=?", new String[]{getString(R.string.app_name)}, null);
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
            System.out.println("已经存在快捷方式");
        }
        return isInstallShortcut;
    }

    private void addShortcut(String name) {
        Intent addShortcutIntent = new Intent(ACTION_ADD_SHORTCUT);

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(MainActivity.this,
                        R.drawable.network));

        // 设置关联程序
        Intent launcherIntent = new Intent(Intent.ACTION_CREATE_SHORTCUT);
//        ComponentName componentName = new ComponentName(getPackageName(), "com.test.MainActivity");
//        launcherIntent.setComponent(componentName);
        launcherIntent.setClass(MainActivity.this, TestActivity.class);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        addShortcutIntent
                .putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        sendBroadcast(addShortcutIntent);
    }

    private void removeShortcut(String name) {
        // remove shortcut的方法在小米系统上不管用，在三星上可以移除
        Intent intent = new Intent(ACTION_REMOVE_SHORTCUT);

        // 名字
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 设置关联程序
        Intent launcherIntent = new Intent(MainActivity.this,
                MainActivity.class).setAction(Intent.ACTION_MAIN);

        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        sendBroadcast(intent);
    }


    public void onll(View view) {
        startActivity(new Intent(MainActivity.this, CycleActivity.class));
        TextView ll = (TextView) findViewById(R.id.ll);
        Resources resources = getResources();
        String u = resources.getQuantityString(R.plurals.unicornCunt, 0);
        ll.setText(u);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
//activity已经更新了
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            当是竖屏的时候
        }
    }

    private float r = 0;

    @Override
    public void onClick(View v) {
        r += 15;
        L.i("aaaarrrrrrrrr" + r);
        l1.setBearing(r);
    }
}
