package com.ll.test.sql;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import java.util.Calendar;

/**
 * Created by LL on 2016/9/1.
 */
public class LocalContentProvider extends Activity {
    //    媒体
    {
//     内部储存
//     MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//     外部储存
//     MediaStore.Audio.Media.INTERNAL_CONTENT_URI;

    }

    //    获取外部卷上的每一个音频cursor,并提取歌曲名称和专辑名称
    private String[] getSongListing() {
        /**
         * Listing 8-35: Accessing the Media Store Content Provider
         */
        // Get a Cursor over every piece of audio on the external volume,
        // extracting the song title and album name.
        String[] projection = new String[]{
                MediaStore.Audio.AudioColumns.ALBUM,
                MediaStore.Audio.AudioColumns.TITLE
        };

        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor =
                getContentResolver().query(contentUri, projection,
                        null, null, null);

        // Get the index of the columns we need.
        int albumIdx =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.ALBUM);
        int titleIdx =
                cursor.getColumnIndexOrThrow(MediaStore.Audio.AudioColumns.TITLE);

        // Create an array to store the result set.
        String[] result = new String[cursor.getCount()];

        // Iterate over the Cursor, extracting each album name and song title.
        while (cursor.moveToNext()) {
            // Extract the song title.
            String title = cursor.getString(titleIdx);
            // Extract the album name.
            String album = cursor.getString(albumIdx);

            result[cursor.getPosition()] = title + " (" + album + ")";
        }

        // Close the Cursor.
        cursor.close();

        //
        return result;
    }

    //    <uses-permission android:name="android.permission.READ_CONTACTS"/>
//    contact,rawcontact,data三层结构
//    得到contacts中每个人的id，姓名
    private String[] getNames() {
        /**
         * Listing 8-36: Accessing the Contacts Contract Contact Content Provider
         */
        // Create a projection that limits the result Cursor
        // to the required columns.
        String[] projection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };

        // Get a Cursor over the Contacts Provider.
//        获取联系人cursor
//        查询data 联系人详情，电话，邮箱，地址
//        getContentResolver().query(ContactsContract.Contacts.CONTENT_FILTER_URI;
        Cursor cursor =
                getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                        projection, null, null, null);

        // Get the index of the columns.
        int nameIdx =
                cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);
        int idIdx =
                cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID);

        // Initialize the result set.
        String[] result = new String[cursor.getCount()];

        // Iterate over the result Cursor.
        while (cursor.moveToNext()) {
            // Extract the name.
            String name = cursor.getString(nameIdx);
            // Extract the unique ID.
            String id = cursor.getString(idIdx);

            result[cursor.getPosition()] = name + " (" + id + ")";
        }

        // Close the Cursor.
        cursor.close();

        //
        return result;
    }

    //联系人详情，电话，地址，邮箱
    private String[] getNameAndNumber() {
        /**
         * Listing 8-37: Finding contact details for a contact name
         */
        ContentResolver cr = getContentResolver();
        String[] result = null;

//         Find a contact using a partial name match
//        使用部分姓名匹配找到联系人
        String searchName = "andy";
        Uri lookupUri =
                Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,
                        searchName);

        // Create a projection of the required column names.
        String[] projection = new String[]{
                ContactsContract.Contacts._ID
        };

        // Get a Cursor that will return the ID(s) of the matched name.
        Cursor idCursor = cr.query(lookupUri,
                projection, null, null, null);

        // Extract the first matching ID if it exists.
//        如果有就获取第一个人的id
        String id = null;
        if (idCursor.moveToFirst()) {
            int idIdx =
                    idCursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID);
            id = idCursor.getString(idIdx);
        }

        // Close that Cursor.
        idCursor.close();

        // Create a new Cursor searching for the data associated with the returned Contact ID.
//        查找相关id的数据
        if (id != null) {
            // Return all the PHONE data for the contact.
//            过滤详细
            String where = ContactsContract.Data.CONTACT_ID +
                    " = " + id + " AND " +
                    ContactsContract.Data.MIMETYPE + " = '" +
                    ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE +
                    "'";

            projection = new String[]{
                    ContactsContract.Data.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
            };

            Cursor dataCursor =
                    getContentResolver().query(ContactsContract.Data.CONTENT_URI,
                            projection, where, null, null);

            // Get the indexes of the required columns.
            int nameIdx =
                    dataCursor.getColumnIndexOrThrow(ContactsContract.Data.DISPLAY_NAME);
            int phoneIdx =
                    dataCursor.getColumnIndexOrThrow(
                            ContactsContract.CommonDataKinds.Phone.NUMBER);

            result = new String[dataCursor.getCount()];

            while (dataCursor.moveToNext()) {
                // Extract the name.
//                姓名
                String name = dataCursor.getString(nameIdx);
//                电话
                // Extract the phone number.
                String number = dataCursor.getString(phoneIdx);

                result[dataCursor.getPosition()] = name + " (" + number + ")";
            }

            dataCursor.close();
        }

        return result;
    }

    //    根据电话号码查找
    private String performCallerId() {
        /**
         * Listing 8-38: Performing a caller-ID lookup
         */
        String incomingNumber = "(650)253-0000";
        String result = "Not Found";

        Uri lookupUri =
                Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                        incomingNumber);

        String[] projection = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME
        };

        Cursor cursor = getContentResolver().query(lookupUri,
                projection, null, null, null);

        if (cursor.moveToFirst()) {
            int nameIdx =
                    cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME);

            result = cursor.getString(nameIdx);
        }

        cursor.close();


        return result;
    }

    private static int PICK_CONTACT = 0;

    //调用联系人界面//跳转到选择联系人界面
    private void pickContact() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == PICK_CONTACT) && (resultCode == RESULT_OK)) {
//            resultTextView.setText(data.getData().toString());

            ContentResolver reContentResolverol = getContentResolver();
            Uri contactData = data.getData();
            //查询就是输入URI等参数,其中URI是必须的,其他是可选的,如果系统能找到URI对应的ContentProvider将返回一个Cursor对象.
            Cursor cursor = managedQuery(contactData, null, null, null, null);
            if (cursor.moveToFirst()) {
                //获得DATA表中的名字
                cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//            list.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                //条件为联系人ID
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                // 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
                Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                        null,
                        null);
                while (phone.moveToNext()) {
                    (phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))).replaceAll(" ", "");
//                list.add(phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                }

            }
        }
    }

    //    插入联系人
    private void insertContactWithIntent() {
        /**
         * Listing 8-40: Inserting a new contact using an Intent
         */
        Intent intent =
                new Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT,
                        ContactsContract.Contacts.CONTENT_URI);
        intent.setData(Uri.parse("tel:(650)253-0000"));
//公司
        intent.putExtra(ContactsContract.Intents.Insert.COMPANY, "Google");
//        邮局
        intent.putExtra(ContactsContract.Intents.Insert.POSTAL,
                "1600 Amphitheatre Parkway, Mountain View, California");

        startActivity(intent);

    }
//    <uses-permission android:name="android.permission.WRITE_CONTACTS" />
//    直接操作联系人数据库，信息


    //    <uses-permission android:name="android.permission.READ_CALENDAR" />
//查询日期事件，获取名称和id
    private String[] queryCalendar() {

        /**
         * Listing 8-41: Querying the Events table
         */
        //Create a projection that limits the result Cursor
        //to the required columns.
        String[] projection = {
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE
        };

        //Get a Cursor over the Events Provider.
//        检查权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Cursor cursor =
                getContentResolver().query(CalendarContract.Events.CONTENT_URI,
                        projection, null, null, null);

        //Get the index of the columns.
        int nameIdx =
                cursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE);
        int idIdx = cursor.getColumnIndexOrThrow(CalendarContract.Events._ID);

        //Initialize the result set.
        String[] result = new String[cursor.getCount()];

        //Iterate over the result Cursor.
        while (cursor.moveToNext()) {
            // Extract the name.
            String name = cursor.getString(nameIdx);
            // Extract the unique ID.
            String id = cursor.getString(idIdx);

            result[cursor.getPosition()] = name + " (" + id + ")";
        }

        //Close the Cursor.
        cursor.close();

        //
        return result;
    }

    //    创建事件，不需要权限
    private void insertNewEventIntoCalendar() {
        /**
         * Listing 8-42: Inserting a new calendar event using an Intent
         */
        // Create a new insertion Intent.
        Intent intent = new Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI);

        // Add the calendar event details
//        标题
        intent.putExtra(CalendarContract.Events.TITLE, "Launch!");
//        描述
        intent.putExtra(CalendarContract.Events.DESCRIPTION,
                "Professional Android 4 " +
                        "Application Development release!");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Wrox.com");
//开始时间
        Calendar startTime = Calendar.getInstance();
        startTime.set(2012, 2, 13, 0, 30);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis());

        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        // Use the Calendar app to add the new event.
//        添加
        startActivity(intent);
    }
//    编辑日历事件,

    private void editCalendarEvent() {
        /**
         * Listing 8-43: Editing a calendar event using an Intent
         */
        // Create a URI addressing a specific event by its row ID.
        // Use it to  create a new edit Intent.
//        必须知道id
        long rowID = 760;
        Uri uri = ContentUris.withAppendedId(
                CalendarContract.Events.CONTENT_URI, rowID);

        Intent intent = new Intent(Intent.ACTION_EDIT, uri);

        // Modify the calendar event details
        Calendar startTime = Calendar.getInstance();
        startTime.set(2012, 2, 13, 0, 30);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis());

        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        // Use the Calendar app to edit the event.
        startActivity(intent);
    }

    //    显示日历和日历事件
    private void displayCalendarEvent() {
        /**
         * Listing 8-44: Displaying a calendar event using an Intent
         */
        // Create a URI that specifies a particular time to view.
        Calendar startTime = Calendar.getInstance();
        startTime.set(2012, 2, 13, 0, 30);

        Uri uri = Uri.parse("content://com.android.calendar/time/" +
                String.valueOf(startTime.getTimeInMillis()));
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        // Use the Calendar app to view the time.
        startActivity(intent);
    }
//    <uses-permission android:name="android.permission.WRITE_CALENDAR" /
//直接修改是要权限的

}
