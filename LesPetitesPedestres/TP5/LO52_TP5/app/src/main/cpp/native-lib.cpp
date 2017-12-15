#include <jni.h>
#include <string>
#include <string.h>
#include <stdlib.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_lpp_lo52_1tp5_LO52MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}



extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_lpp_lo52_1tp5_LO52MainActivity_read(
        JNIEnv* env,
jobject /* this */, jstring x, jstring y) {
    const char *nativeX = env->GetStringUTFChars(x, 0);
    const char *nativeY = env->GetStringUTFChars(y, 0);
    char buff[100];
    strcpy(buff, nativeX);
    strcat(buff, nativeY);
    return env->NewStringUTF(buff);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_lpp_lo52_1tp5_LO52MainActivity_write(
        JNIEnv* env,
        jobject /* this */, jstring x) {
    const char *name = env->GetStringUTFChars(x, 0);
    char buff[100];
    strcpy(buff, "Hello C++ from ");
    strcat(buff, name);

    return env->NewStringUTF(buff);
}

extern "C"
JNIEXPORT jint JNICALL
Java_fr_utbm_lpp_lo52_1tp5_LO52MainActivity_start(
        JNIEnv* env,
        jobject /* this */, jint jni_i) {
    jni_i *= jni_i;
    return jni_i;
}

extern "C"
JNIEXPORT jint JNICALL
Java_fr_utbm_lpp_lo52_1tp5_LO52MainActivity_stop(
        JNIEnv* env,
        jobject /* this */, jint jni_i) {
    jni_i *= jni_i;
    return jni_i;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_lpp_lo52_1tp5_LO52MainActivity_reset(
        JNIEnv* env,
        jobject /* this */) {

    return env->NewStringUTF(strerror(1 + rand()%131));
}
