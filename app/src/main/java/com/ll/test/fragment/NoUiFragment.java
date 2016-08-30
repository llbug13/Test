package com.ll.test.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by LL on 2016/8/23.
 */
public class NoUiFragment extends Fragment {
    /**
     * fragmentTransaction.add(new Fragment(),"asd");
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        获取父activity的类型安全的引用
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        创建后台工作线程和任务
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        初始化工作线程和任务
    }
}
