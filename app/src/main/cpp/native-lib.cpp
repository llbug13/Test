#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_ll_test_ndk_NDKTest_stringFromJNI(JNIEnv *env, jclass type) {

    // TODO
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());

}

//extern "C"
//jstring
//Java_com_example_ll_myapplication_MainActivity_stringFromJNI(
//        JNIEnv *env,
//        jobject /* this */) {
//    std::string hello = "Hello from C++";
//    return env->NewStringUTF(hello.c_str());
//}
