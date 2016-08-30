package com.ll.test.save;

import android.preference.PreferenceActivity;

import com.ll.test.R;

import java.util.List;

public class MyFragmentPreferenceActivity extends PreferenceActivity {

  @Override
  public void onBuildHeaders(List<Header> target) {
    loadHeadersFromResource(R.xml.preferenceheaders, target);
  }
}