package com.ll.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.ll.test.log.L;

/**
 * Created by LL on 2016/8/22.
 */
public class TestActivity extends Activity {
    private ListView c_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t);
        c_list = (ListView) findViewById(R.id.c_list);
//        LoaderManager.LoaderCallbacks<Cursor> callbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
//
//            @Override
//            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//                CursorLoader cursorLoader = new CursorLoader(TestActivity.this, CallLog.CONTENT_URI, null, null, null, null);
//                return cursorLoader;
//            }
//
//            @Override
//            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//                String[] from = new String[]{CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER};
//                int[] ints = new int[]{R.id.c_1, R.id.c_2};
//                SimpleCursorAdapter adapter;
//                adapter = new SimpleCursorAdapter(TestActivity.this, R.layout.item_cursor_adapter, data, from, ints);
//                c_list.setAdapter(adapter);
//            }
//
//            @Override
//            public void onLoaderReset(Loader<Cursor> loader) {
//
//            }
//        };
//        getLoaderManager().initLoader(0, null, callbacks);

    }

    @Override
    protected void onDestroy() {
        L.i("onDestroyonDestroyonDestroyonDestroyonDestroy");
        super.onDestroy();
    }

    @Override
    public void finish() {
        L.i("onDestroyfinishfinishfinish");
        super.finish();
    }
}
