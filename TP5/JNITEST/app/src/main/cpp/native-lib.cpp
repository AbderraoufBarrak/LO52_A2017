#include <jni.h>
#include <string>
#include <string.h>
#include <stdlib.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_missornela_jnitest_LO52MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_missornela_jnitest_LO52MainActivity_readCmd(JNIEnv *env, jobject t/* this */,
                                                            jstring string1) {
    const char *str = env->GetStringUTFChars(string1, 0);
    std::string read = "Read: ";
    read+= str;
    env->ReleaseStringUTFChars(string1, str);
    return env->NewStringUTF(read.c_str());

}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_missornela_jnitest_LO52MainActivity_writeCmd(JNIEnv *env, jobject /* this */,
                                                             jstring string1) {
    const char *str = env->GetStringUTFChars(string1, 0);
    std::string write = "Write: ";
    write+= str;

    env->ReleaseStringUTFChars(string1, str);
    return env->NewStringUTF(write.c_str());
}



extern "C"
JNIEXPORT jint JNICALL
Java_com_example_missornela_jnitest_LO52MainActivity_stop(JNIEnv *env, jobject /* this */, jint i) {

    // TODO
    i *= i;
    return i;
}


extern "C"
JNIEXPORT jint JNICALL
Java_com_example_missornela_jnitest_LO52MainActivity_start(JNIEnv *env, jobject /* this */, jint i) {

    i *= i;
    return i;

}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_missornela_jnitest_LO52MainActivity_reset(JNIEnv *env, jobject /* this */) {

    return env->NewStringUTF(strerror(rand()%9));

}
