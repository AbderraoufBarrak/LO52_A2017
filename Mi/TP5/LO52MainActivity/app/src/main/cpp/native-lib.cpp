#include <jni.h>
#include <string.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_lo52mainactivity_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_lo52mainactivity_MainActivity_read(
        JNIEnv* env,
        jobject /* this */,
        jstring x){
    const char *str = (char*) env->GetStringUTFChars(x, 0);
    return env->NewStringUTF(str);

}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_lo52mainactivity_MainActivity_write(
        JNIEnv* env,
        jobject /* this */,
        jstring x){
    const char *str = env->GetStringUTFChars(x, 0);
    return env->NewStringUTF(str);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_lo52mainactivity_MainActivity_stop(
        JNIEnv* env,
        jobject /* this */,
        jint i){
    return (jint)i*i;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_lo52mainactivity_MainActivity_start(
        JNIEnv* env,
        jobject /* this */,
        jint i){
    return (jint)i*i;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_lo52mainactivity_MainActivity_reset(
        JNIEnv* env,
        jobject /* this */){
    return env->NewStringUTF(strerror(0));
}