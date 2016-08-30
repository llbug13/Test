package com.ll.test.save;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LL on 2016/8/30.
 */
public class AFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        关联的activity重启（重建）是，fragment不会被终止，重启
        setRetainInstance(true);
//        其他的周期会调用的onDestroy不会
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
//            得到保存
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
//    来更新保存的ui,提高效率
    }
}
