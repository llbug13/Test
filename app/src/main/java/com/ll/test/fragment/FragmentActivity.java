package com.ll.test.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.res.Configuration;
import android.webkit.WebViewFragment;

/**
 * Created by LL on 2016/8/23.
 */
public class FragmentActivity extends Activity {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.findFragmentByTag("ll");
        fragmentTransaction.add(1, new Fragment(), "asd");
        fragmentTransaction.add(new Fragment(), "asd");
        fragmentTransaction.remove(new Fragment());
//        back        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.setCustomAnimations(1, 2);
//        可以定义fragment事件callback接口,activity实现接口
//        DialogFragment
//        ListFragment
//        WebViewFragment
    }
}
