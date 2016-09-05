package com.ll.test.show;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.ll.test.R;

/**
 * Created by LL on 2016/9/5.
 * 操作栏
 */
public class ActionBarTest extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
//        Drawable myDrawable = getResources().getDrawable(R.mipmap.ic_launcher);
//        actionBar.setBackgroundDrawable(myDrawable);
//        actionBar.setDisplayShowTitleEnabled(false);
//        图标和微图标的转换
//        actionBar.setDisplayUseLogoEnabled(true);
//        隐藏图标和微图标
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setTitle("");
//        actionBar.setSubtitle("");
//        actionBar.show();
//        actionBar.hide();
//        重叠到activity
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        View myView = getWindow().getDecorView();
//        隐藏导航栏
        myView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /**
         * Listing 10-21: Reacting to changes in system UI visibility
         */
        myView.setOnSystemUiVisibilityChangeListener(
                new View.OnSystemUiVisibilityChangeListener() {

                    public void onSystemUiVisibilityChange(int visibility) {
                        if (visibility == View.SYSTEM_UI_FLAG_VISIBLE) {
                            // TODO Display Action Bar and Status Bar
//                            显示
                            getActionBar().show();
                        } else {
//                            隐藏
                            // TODO Hide Action Bar and Status Bar
                            getActionBar().hide();
                        }
                    }
                });
    }

}
