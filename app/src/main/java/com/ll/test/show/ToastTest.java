package com.ll.test.show;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by LL on 2016/9/5.
 */
public class ToastTest {
    private void show(Context context) {
        Toast toast = Toast.makeText(context, "a", Toast.LENGTH_LONG);
//        设置位置
        toast.setGravity(Gravity.BOTTOM, 10, 10);
        View view=new View(context);
//        自定义布局
        toast.setView(view);
//        显示
//        在主线程中显示
        toast.show();
    }
}
