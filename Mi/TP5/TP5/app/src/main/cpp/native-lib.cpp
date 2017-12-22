#include <jni.h>
#include <string>
#include <sstream>
#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_lo52_dewback_tp5_LO52MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT jstring JNICALL Java_com_lo52_dewback_tp5_LO52MainActivity_read(JNIEnv * env, jobject obj, jstring myString)
{
    string foo("Read: ");
    string tmp = foo + env->GetStringUTFChars(myString, 0);

    return (env)->NewStringUTF(tmp.c_str());
}

JNIEXPORT jstring JNICALL Java_com_lo52_dewback_tp5_LO52MainActivity_write(JNIEnv *env, jobject obj, jstring myString)
{
    string foo("write: ");
    string tmp = foo + env->GetStringUTFChars(myString, 0);

    return (env)->NewStringUTF(tmp.c_str());
}

JNIEXPORT jstring JNICALL Java_com_lo52_dewback_tp5_LO52MainActivity_stop(JNIEnv *env, jobject obj, jint myInt)
{
    int myInt1 = myInt;
    int myInt2 = myInt* myInt;
    std::ostringstream oss;
    oss << myInt1 << "/" << myInt2;
    string tmp = oss.str();

    return (env)->NewStringUTF(tmp.c_str());

}

JNIEXPORT jstring JNICALL Java_com_lo52_dewback_tp5_LO52MainActivity_start(JNIEnv *env, jobject obj, jint myInt)
{
    int myInt1 = myInt;
    int myInt2 = myInt* myInt;
    std::ostringstream oss;
    oss << myInt1 << "/" << myInt2;
    string tmp = oss.str();

    return (env)->NewStringUTF(tmp.c_str());
}

JNIEXPORT jstring JNICALL Java_com_lo52_dewback_tp5_LO52MainActivity_reset(JNIEnv *env, jobject obj)
{
    srand((unsigned) time(0));

    int i = rand()%2;

    switch (i)
    {
        case 0:
            return (env)->NewStringUTF("0 = success");
        case 1:
            return (env)->NewStringUTF("1 = you failed");

        default:
            return (env)->NewStringUTF("bad test");
    }
}

