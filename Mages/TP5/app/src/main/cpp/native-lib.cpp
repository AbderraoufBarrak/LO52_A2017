#include <jni.h>
#include <string>
#include <string.h>
#include <stdlib.h>

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_example_tp5_tp5_LO52MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_tp5_tp5_LO52MainActivity_read(JNIEnv *env, jobject instance, jstring s_) {
const char *s = env->GetStringUTFChars(s_, 0);
std::string read = "Read: ";
read+= s;
env->ReleaseStringUTFChars(s_, s);
return env->NewStringUTF(read.c_str());
}extern "C"

JNIEXPORT jstring JNICALL
Java_com_example_tp5_tp5_LO52MainActivity_write(JNIEnv *env, jobject instance,jstring s_) {
const char *s = env->GetStringUTFChars(s_, 0);
std::string write = "Write: ";
write+= s;
env->ReleaseStringUTFChars(s_, s);
return env->NewStringUTF(write.c_str());
}extern "C"

JNIEXPORT jint JNICALL
Java_com_example_tp5_tp5_LO52MainActivity_stop(JNIEnv *env, jobject instance, jint i) {
i *= i;
return i;
}extern "C"

JNIEXPORT jint JNICALL
Java_com_example_tp5_tp5_LO52MainActivity_start(JNIEnv *env, jobject instance, jint i) {
i *= i;
return i;
}extern "C"

JNIEXPORT jstring JNICALL
Java_com_example_tp5_tp5_LO52MainActivity_reset(JNIEnv *env, jobject instance) {
 return env->NewStringUTF(strerror(rand()%20));
}


