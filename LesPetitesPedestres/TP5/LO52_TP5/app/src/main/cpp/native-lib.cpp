#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_lpp_lo52_1tp5_LO52MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
