package com.ll.test.save;

import android.content.res.Resources;

import com.ll.test.R;
import com.ll.test.app.app;

import java.io.InputStream;

/**
 * Created by LL on 2016/8/30.
 */
public class raw {

    private void raw() {
        Resources resources = app.getContext().getResources();
        InputStream myFile = resources.openRawResource(R.raw.ic_launcher);
    }
}
