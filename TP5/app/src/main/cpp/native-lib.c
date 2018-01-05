#include <string.h>
#include <jni.h>
#include <stdlib.h>
#include <time.h>

JNIEXPORT jstring JNICALL
Java_com_example_utilisateur_tp5_LO52MainActivity_read( JNIEnv* env, jobject thiz, jstring val, jboolean read)
{
    char* start = read ? "Read : " : "Write : ";
    char* all = malloc(strlen(start) + strlen(val));
    strcpy(all, start);
    strcat(all, (*env)->GetStringUTFChars(env, val, NULL));

    jstring result = (*env)->NewStringUTF(env, all);

    free(all);

    return result;
}

JNIEXPORT jint JNICALL
Java_com_example_utilisateur_tp5_LO52MainActivity_square( JNIEnv* env, jobject thiz, jint val)
{
    return val * val;
}

JNIEXPORT jstring JNICALL
Java_com_example_utilisateur_tp5_LO52MainActivity_reset( JNIEnv* env, jobject thiz)
{
    srand(time(NULL));   // should only be called once
    int r = rand()%3;

    switch (r){
        case 0 :
            return (*env)->NewStringUTF(env, "Success");
        case 1 :
            return (*env)->NewStringUTF(env, "Error : code != 0");
        default:
            return (*env)->NewStringUTF(env, "General Error");

    }
}