#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_ll_test_ndk_NDKTest_stringFromJNI(JNIEnv *env, jclass type) {

    // TODO
    std::string hello = "Hello from C++";
    double obj = 3.14;
    void *o = &obj;
    int i = 10;
    int *const i1 = &i;
    const int i4 = 3;
//    指向常量的常量指针
    const int *const i3 = &i4;
    jclass classz;
    classz = env->FindClass("");
    if (classz == NULL) {

    }
//    AndroidRuntime::registerNativeMethods(env,
//                                          "android/media/MediaPlayer", gMethods, NELEM(gMethods));
    jclass clazz;

    clazz = env->FindClass("android/media/MediaPlayer");
    if (clazz == NULL) {
    }

//    fields.context = env->GetFieldID(clazz, "mNativeContext", "I");
//    if (fields.context == NULL) {
//    }
//
//    fields.post_event = env->GetStaticMethodID(clazz, "postEventFromNative",
//                                               "(Ljava/lang/Object;IIILjava/lang/Object;)V");
//    if (fields.post_event == NULL) {
//    }
//
//    fields.surface_texture = env->GetFieldID(clazz, "mNativeSurfaceTexture", "I");

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
