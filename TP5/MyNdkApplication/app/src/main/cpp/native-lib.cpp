#include <jni.h>
#include <string>
#include <string.h>
#include <stdlib.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_tp5_utbm_fr_myndkapplication_LO52MainActivity_read(
        JNIEnv *env,
        jobject /* this */, jstring str) {

    const char *nativeString = env->GetStringUTFChars(str,NULL);
    std::string retour = "READ : ";
    retour += nativeString;
    // + *nativeString;
    env->ReleaseStringUTFChars(str,nativeString);
    return env->NewStringUTF(retour.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_tp5_utbm_fr_myndkapplication_LO52MainActivity_write(
        JNIEnv *env,
        jobject /* this */, jstring str) {

    const char *nativeString = env->GetStringUTFChars(str,NULL);
    std::string retour = "WRITE : ";
    retour += nativeString;
    // + *nativeString;
    env->ReleaseStringUTFChars(str,nativeString);
    return env->NewStringUTF(retour.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_tp5_utbm_fr_myndkapplication_LO52MainActivity_stop(
        JNIEnv *env,
        jobject /* this */, jint i) {

    return i*i;
}

extern "C"
JNIEXPORT jint JNICALL
Java_tp5_utbm_fr_myndkapplication_LO52MainActivity_start(
        JNIEnv *env,
        jobject /* this */, jint i) {

    return i*i;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_tp5_utbm_fr_myndkapplication_LO52MainActivity_reset(
        JNIEnv *env,
        jobject /* this */, jstring str) {

    int numError = rand()%10;

    if (numError == 0){
        return env->NewStringUTF("Succès");
    }
    else{
        return env->NewStringUTF(strerror(numError));
    }

}






