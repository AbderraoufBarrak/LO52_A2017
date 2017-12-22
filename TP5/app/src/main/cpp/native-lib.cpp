#include <jni.h>
#include <string>
#include <time.h>
#include <stdlib.h>
#include <sstream>

extern "C"
JNIEXPORT jstring

JNICALL
Java_fr_utbm_tp5_LO52MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_fr_utbm_tp5_LO52MainActivity_read(
        JNIEnv *env, jobject /* this */, jstring fromRead) {
    const char *nativeString = env->GetStringUTFChars(fromRead, NULL);
    std::string print = nativeString;
    env->ReleaseStringUTFChars(fromRead,nativeString);
    return env->NewStringUTF(print.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_fr_utbm_tp5_LO52MainActivity_stop(JNIEnv *env, jobject /* this */, jint alea) {
    std::string printAlea;
    std::string printDoubleAlea;
    std::string print;
    std::ostringstream os;
    std::ostringstream os2;
    os << alea;
    printAlea = os.str();
    os2 << alea*alea;
    printDoubleAlea=os2.str();

    print = printAlea + "/" + printDoubleAlea;
    return env->NewStringUTF(print.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_fr_utbm_tp5_LO52MainActivity_reset(JNIEnv *env, jobject /* this */) {
    std::string printError;
    std::ostringstream os;

    srand((unsigned) time(NULL));

    int alea = rand() % 15;
    os << alea;

    if (alea == 0){
        printError = "Success : " + os.str();
    } else{
        printError = "Error : " + os.str();
    }

    return env->NewStringUTF(printError.c_str());
}


