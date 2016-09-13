package com.ll.test.sql;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created by LL on 2016/8/31.
 * 一般访问数据库
 * 也可以访问文件
 */
public class ContentProviderTest extends ContentProvider {
//    public static final Uri CONTENT_URI = Uri.parse("com.bgtx.runquick.provider.test/elenemts");
//    //    所有
//    private static final int ALLROWS = 1;
//    //    单行
//    private static final int SINGLE_ROW = 2;
    //    解析uri
//    private static final UriMatcher uriMatcher;


    private static final int ALLROWS = 1;
    private static final int SINGLE_ROW = 2;

    private static final UriMatcher uriMatcher;
    public static final String KEY_ID = "_id";
    public static final String KEY_COLUMN_1_NAME = "KEY_COLUMN_1_NAME";
    private SQLiteTest.HoardDBOpenHelper myOpenHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.paad.skeletondatabaseprovider",
                "elements", ALLROWS);
        uriMatcher.addURI("com.paad.skeletondatabaseprovider",
                "elements/#", SINGLE_ROW);
    }

    @Override
    public boolean onCreate() {
        myOpenHelper = new SQLiteTest.HoardDBOpenHelper(getContext(),
                SQLiteTest.HoardDBOpenHelper.DATABASE_NAME, null,
                SQLiteTest.HoardDBOpenHelper.DATABASE_VERSION);
//        删除，添加，更新都多要使用
//        cursor.registerContentObserver();
//        ContentResolver contentResolver=null;
//        contentResolver.notifyChange();
        return true;
//        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        // Open thedatabase.
        SQLiteDatabase db;
        try {
            db = myOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = myOpenHelper.getReadableDatabase();
        }

        // Replace these with valid SQL statements if necessary.
        String groupBy = null;
        String having = null;

        // Use an SQLite Query Builder to simplify constructing the
        // database query.
//执行查询的便利辅助类
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // If this is a row query, limit the result set to the passed in row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
//                根据条件来设置
                String rowID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(KEY_ID + "=" + rowID);
            default:
                break;
        }

        // Specify the table on which to perform the query. This can
        // be a specific table or a join as required.
//        查询的表
        queryBuilder.setTables(SQLiteTest.HoardDBOpenHelper.DATABASE_TABLE);

        // Execute the query.
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, groupBy, having, sortOrder);
        // Return the result Cursor.
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
//        返回了个字符串,她标识了mime类型
//        、"vnd.android.cursor.dir/vnd.<companame>.<contenttype>";
        switch (uriMatcher.match(uri)) {
            case ALLROWS:
                return "vnd.android.cursor.dir/vnd.paad.elemental";
            case SINGLE_ROW:
                return "vnd.android.cursor.item/vnd.paad.elemental";
            default:
                throw new IllegalArgumentException("Unsupported URI: " +
                        uri);
        }
    }

    public static final Uri CONTENT_URI = Uri.parse("content://com.paad.skeletondatabaseprovider/elements");

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Open a read / write database to support the transaction.
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        // To add empty rows to your database by passing in an empty
        // Content Values object you must use the null column hack
        // parameter to specify the name of the column that can be
        // set to null.
        String nullColumnHack = null;

        // Insert the values into the table
        long id = db.insert(SQLiteTest.HoardDBOpenHelper.DATABASE_TABLE,
                nullColumnHack, values);

        // Construct and return the URI of the newly inserted row.
        if (id > -1) {
            // Construct and return the URI of the newly inserted row.
            Uri insertedId = ContentUris.withAppendedId(CONTENT_URI, id);

            // Notify any observers of the change in the data set.
            getContext().getContentResolver().notifyChange(insertedId, null);

            return insertedId;
        } else
            return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Open a read / write database to support the transaction.
//       打开一个数据库
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        // If this is a row URI, limit the deletion to the specified row.
//        条件
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                selection = KEY_ID + "=" + rowID
                        + (!TextUtils.isEmpty(selection) ?
                        " AND (" + selection + ')' : "");
            default:
                break;
        }

        // To return the number of deleted items you must specify a where
        // clause. To delete all rows and return a value pass in "1".
        if (selection == null)
            selection = "1";

        // Perform the deletion.
//        执行
        int deleteCount = db.delete(SQLiteTest.HoardDBOpenHelper.DATABASE_TABLE,
                selection, selectionArgs);

        // Notify any observers of the change in the data set.
//        通知所有观察，数据集以改变
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the number of deleted items.
//        返回删除的数量
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // Open a read / write database to support the transaction.
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        // If this is a row URI, limit the deletion to the specified row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW:
                String rowID = uri.getPathSegments().get(1);
                selection = KEY_ID + "=" + rowID
                        + (!TextUtils.isEmpty(selection) ?
                        " AND (" + selection + ')' : "");
            default:
                break;
        }

        // Perform the update.
        int updateCount = db.update(SQLiteTest.HoardDBOpenHelper.DATABASE_TABLE,
                values, selection, selectionArgs);

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);

        return updateCount;
    }
}
