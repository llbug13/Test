package com.ll.test.save;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.ll.test.R;

public class MyPreferenceFragment extends PreferenceFragment {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.userpreferences);
  }
}