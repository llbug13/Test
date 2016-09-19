// UU.aidl
package com.ll.test.aidl;
import com.ll.test.aidl.UseParcelable;
// Declare any non-default types here with import statements

interface UU {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
            UseParcelable ll();
}
