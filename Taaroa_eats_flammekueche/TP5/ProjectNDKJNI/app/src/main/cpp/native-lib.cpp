#include <jni.h>
#include <string>
#include <android/log.h>
#include <stdlib.h>     /* srand, rand */
#include <time.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_lo52_projectndkjni_LO52MainActivity_read(
        JNIEnv *env,
        jobject obj,
        jstring str) {
    const char *nativeString = (env)->GetStringUTFChars(str, JNI_FALSE);

    // afficher nativevString
    __android_log_print(ANDROID_LOG_VERBOSE,"READ_native=nativeString","%s",nativeString);

    // méthode pour concaténer les 2 types : string + nativeString
    std::string label = "Read: ";
    label += nativeString;

    // afficher string
    __android_log_print(ANDROID_LOG_VERBOSE,"READ_native=label","%s",label.c_str());

    // vider mémoire ?
    (env)->ReleaseStringUTFChars(str, nativeString);
    return env->NewStringUTF(label.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_lo52_projectndkjni_LO52MainActivity_write(
        JNIEnv *env,
        jobject obj,
        jstring str) {
    const char *nativeString = (env)->GetStringUTFChars(str, JNI_FALSE);

    // méthode pour concaténer les 2 types : string + nativeString
    std::string label = "Write: ";
    label += nativeString;

    // vider mémoire ?
    (env)->ReleaseStringUTFChars(str, nativeString);
    return env->NewStringUTF(label.c_str());
}

extern "C"
JNIEXPORT jint JNICALL
Java_fr_utbm_lo52_projectndkjni_LO52MainActivity_stop(
        JNIEnv *env,
        jobject obj,
        jint nb) {

    int nbSquare = (int) nb*nb;

    return nbSquare;
}

extern "C"
JNIEXPORT jint JNICALL
Java_fr_utbm_lo52_projectndkjni_LO52MainActivity_start(
        JNIEnv *env,
        jobject obj,
        jint nb) {

    int nbSquare = (int) nb*nb;

    return nbSquare;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_lo52_projectndkjni_LO52MainActivity_reset(
        JNIEnv *env,
        jobject obj) {

    std::string label;
    // rand() % (inter=max+1) + min
    srand(time(NULL));
    int r = rand() % 3 + 0;

    switch (r){
        case 0:
            label = "0: success!";
            break;
        case 1:
            label = "1: almost!";
            break;
        case 2:
            label = "2: error!";
            break;
    }

    return env->NewStringUTF(label.c_str());
}

