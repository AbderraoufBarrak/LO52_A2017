#include <jni.h>
#include <string>

#include <iostream>
#include <string.h>
#include <stdlib.h>

extern "C"

JNIEXPORT jstring JNICALL Java_fr_utbm_ffbad_lo52tp5jni_JNIMethods_read(JNIEnv *env, jobject obj, jstring fieldValue)
{
    const std::string fieldValueChar = env->GetStringUTFChars(fieldValue,0);
    std::string returnString = "Read : " + fieldValueChar;
    return env->NewStringUTF(returnString.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL Java_fr_utbm_ffbad_lo52tp5jni_JNIMethods_write(JNIEnv *env, jobject obj, jstring fieldValue)
{
    const std::string fieldValueChar = env->GetStringUTFChars(fieldValue,0);
    std::string returnString = "Write : " + fieldValueChar;
    return env->NewStringUTF(returnString.c_str());
}

extern "C"
JNIEXPORT jint JNICALL Java_fr_utbm_ffbad_lo52tp5jni_JNIMethods_square(JNIEnv *env, jobject obj, jint number)
{
    return number * number;
}

extern "C"
JNIEXPORT jstring JNICALL Java_fr_utbm_ffbad_lo52tp5jni_JNIMethods_errorCode(JNIEnv *env, jobject obj)
{

    srand (time(NULL));

    /* generate secret number between 0 and 2: */
    int randomCode = rand() % 3;

    switch(randomCode){

        case 0:
            return env->NewStringUTF("Success (errorCode 0)");

        case 1:
            return env->NewStringUTF("Tatan elle fait des flans (errorCode 1)");

        case 2:
            return env->NewStringUTF("Sire, on en a gros (errorCode 2)");

        default:
            break;

    }


}
extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_ffbad_lo52_1tp5_1jni_LO52MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
