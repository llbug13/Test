package com.ll.test.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.UUID;

/**
 * Created by LL on 2016/8/31.
 * 数据库的位置
 * /data/data/<package>/databases
 */
public class SQLiteTest {
    //The index (key) column name for use in where clauses.索引（建）列的名称
    public static final String KEY_ID = "_id";
    //The name and column index of each column in your database.
    //These should be descriptive.
//    每列的名称具有描述性
    public static final String KEY_GOLD_HOARD_NAME_COLUMN =
            "GOLD_HOARD_NAME_COLUMN";
    public static final String KEY_GOLD_HOARD_ACCESSIBLE_COLUMN =
            "OLD_HOARD_ACCESSIBLE_COLUMN";
    public static final String KEY_GOLD_HOARDED_COLUMN =
            "GOLD_HOARDED_COLUMN";

    {
        UUID.randomUUID().toString().replace("-", "");
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }


    private static class HoardDBOpenHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "myDatabase.db";
        private static final String DATABASE_TABLE = "GoldHoards";
        private static final int DATABASE_VERSION = 1;

        // SQL Statement to create a new database.
//        创建
        private static final String DATABASE_CREATE = "create table " +
                DATABASE_TABLE + " (" + KEY_ID +
                " integer primary key autoincrement, " +
                KEY_GOLD_HOARD_NAME_COLUMN + " text not null, " +
                KEY_GOLD_HOARDED_COLUMN + " float, " +
                KEY_GOLD_HOARD_ACCESSIBLE_COLUMN + " integer);";

        public HoardDBOpenHelper(Context context, String name,
                                 SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
        }

        // Called when there is a database version mismatch meaning that
        // the version of the database on disk needs to be upgraded to
        // the current version.
//        版本不一致时样更新旧表
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion) {
            // Log the version upgrade.
            Log.w("TaskDBAdapter", "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");

            // Upgrade the existing database to conform to the new
            // version. Multiple previous versions can be handled by
            // comparing oldVersion and newVersion values.
//删除旧表
            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
            // Create a new one.
            onCreate(db);
        }
    }
}
