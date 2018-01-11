#include <jni.h>
#include <string>
#include "string.h"
#include <cstdlib>

std::string jstring2string(JNIEnv *env, jstring jStr) {
    if (!jStr)
        return "";

    const jclass stringClass = env->GetObjectClass(jStr);
    const jmethodID getBytes = env->GetMethodID(stringClass, "getBytes", "(Ljava/lang/String;)[B");
    const jbyteArray stringJbytes = (jbyteArray) env->CallObjectMethod(jStr, getBytes, env->NewStringUTF("UTF-8"));

    size_t length = (size_t) env->GetArrayLength(stringJbytes);
    jbyte* pBytes = env->GetByteArrayElements(stringJbytes, NULL);

    std::string ret = std::string((char *)pBytes, length);
    env->ReleaseByteArrayElements(stringJbytes, pBytes, JNI_ABORT);

    env->DeleteLocalRef(stringJbytes);
    env->DeleteLocalRef(stringClass);
    return ret;
}

extern "C"
JNIEXPORT jstring JNICALL Java_fr_utbm_lo52_a17_tp5_LO52MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Affichage du texte";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL Java_fr_utbm_lo52_a17_tp5_LO52MainActivity_readCmd(
        JNIEnv *env,
        jobject /* this */,
        jstring str) {
    std::string hello = "Read : ";
    hello += jstring2string(env,str);

//    hello = strcat(hello,jstring2string(env,str));
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL Java_fr_utbm_lo52_a17_tp5_LO52MainActivity_writeCmd(
        JNIEnv *env,
        jobject /* this */,
        jstring str) {
    std::string hello = "Write : ";
    hello += jstring2string(env,str);
    //hello = strcat(hello,str);
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL Java_fr_utbm_lo52_a17_tp5_LO52MainActivity_startCmd(
        JNIEnv *env,
        jobject /* this */,
        jint  nbr) {
    char buf[20]; // assumed large enough to cope with result

    sprintf(buf, "%d/%d", nbr,nbr*nbr);  // error checking omitted

    return env->NewStringUTF(buf);

}

extern "C"
JNIEXPORT jstring JNICALL Java_fr_utbm_lo52_a17_tp5_LO52MainActivity_stopCmd(
        JNIEnv *env,
        jobject /* this */,
        jint nbr) {
    char buf[20]; // assumed large enough to cope with result

    sprintf(buf, "%d/%d", nbr,nbr*nbr);  // error checking omitted

    return env->NewStringUTF(buf);
    std::string hello = "Write : ";
    //hello = strcat(hello,str);
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL Java_fr_utbm_lo52_a17_tp5_LO52MainActivity_reset(
        JNIEnv *env,
        jobject /* this */) {

    char buf[20]; // assumed large enough to cope with result
    int rnd = rand()%10;
    if(rnd>0)
        sprintf(buf, "Error num %d", rnd);
    else
        sprintf(buf, "SUCCESS : %d", rnd);


    return env->NewStringUTF(buf);
}
