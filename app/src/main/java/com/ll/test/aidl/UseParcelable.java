package com.ll.test.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LL on 2016/9/19.
 */
public class UseParcelable implements Parcelable {
    private String ll;

    protected UseParcelable(Parcel in) {
        ll = in.readString();
    }

    public static final Creator<UseParcelable> CREATOR = new Creator<UseParcelable>() {
        @Override
        public UseParcelable createFromParcel(Parcel in) {
            return new UseParcelable(in);
        }

        @Override
        public UseParcelable[] newArray(int size) {
            return new UseParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        写入到parcel
        dest.writeString(ll);
    }
}
