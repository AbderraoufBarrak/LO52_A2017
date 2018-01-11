#include <jni.h>
#include <string>
#include <iostream>
#include <sstream>

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_houssame_lo52_1tp5_LO52MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Text Field";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_houssame_lo52_1tp5_LO52MainActivity_readCmd(
        JNIEnv *env,
        jobject /* this */,
        jstring jIn) {
    //const char *path = env->GetStringUTFChars(jIn, NULL);
    //std::string cIn = ToString(path);
    const char *nativeString = (env)->GetStringUTFChars(jIn, 0);
    std::string cReturn = "READ : ";
    cReturn += nativeString;

    return env->NewStringUTF(cReturn.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_houssame_lo52_1tp5_LO52MainActivity_writeCmd(
        JNIEnv *env,
        jobject /* this */,
        jstring jIn) {
    //const char *path = env->GetStringUTFChars(jIn, NULL);
    //std::string cIn = ToString(path);


    const char *nativeString = (env)->GetStringUTFChars(jIn, 0);
    std::string cReturn = "Write : ";
    cReturn += nativeString;

    return env->NewStringUTF(cReturn.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_houssame_lo52_1tp5_LO52MainActivity_stopCmd(
        JNIEnv *env,
        jobject /* this */,
        jint jIn) {
    int cOut = jIn * jIn;
    std::string csNum;
    std::string cReturn;
    //convesion Int to String
    std::ostringstream ostr;
    std::ostringstream ostr1;
    ostr << cOut;
    ostr1 << jIn;
    csNum = ostr.str();
    cReturn = ostr1.str();

    //end
    cReturn += "/";
    cReturn += csNum;


    return env->NewStringUTF(cReturn.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_houssame_lo52_1tp5_LO52MainActivity_resetCmd(
        JNIEnv *env,
        jobject /* this */,
        jint jIn) {
    int cOut = jIn;
    std::string cReturn;

    switch (cOut) {
        case 0 :
            cReturn = "Succes";
            break;
        case 1 :
            cReturn = "Error1";
            break;
        case 2 :
            cReturn = "Error2";
            break;
        case 3 :
            cReturn = "Error3";
            break;
    }



    return env->NewStringUTF(cReturn.c_str());
}
