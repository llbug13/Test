package com.ll.test.save;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.provider.Settings;

import com.ll.test.R;

/**
 * Created by LL on 2016/8/30.
 */
public class SharedPreferencesActivity extends PreferenceActivity {
    {
        Settings settings = null;
        PreferenceFragment preferenceFragment = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.userpreferences);
    }
}
