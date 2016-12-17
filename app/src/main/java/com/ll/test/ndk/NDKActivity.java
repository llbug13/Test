package com.ll.test.ndk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.ll.test.R;
import com.ll.test.base.BActivity;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by ll on 16-10-28.
 */

public class NDKActivity extends BActivity {
    private TextView ndk_text;

    private final String s=ll();

    public NDKActivity() {
//        s = "";

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ndk);
        ndk_text = (TextView) findViewById(R.id.ndk_text);
        set();
    }

    private void set() {
//        设置时区
        Locale.setDefault(Locale.ITALY);
        Date date = new Date();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        date.clone();
        Calendar calendar = Calendar.getInstance();
        String[][] i = new String[10][];
        i[0] = i[1];
        String[] weekDayName = new DateFormatSymbols().getShortWeekdays();
        int u;
        if (i[0] == i[1]) {
            ndk_text.setText(NDKTest.stringFromJNI() + "lllk" + i[1]);
        }
//        ndk_text.setText(NDKTest.stringFromJNI());
        ll(this);
    }

    private void ll(final NDKActivity s) {

    }

    private void ll(final BActivity s) {

    }
    private String ll(){
     return "";
    }
}
