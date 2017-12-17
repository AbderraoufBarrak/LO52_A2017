//
// Created by kiera on 15/12/2017.
//

#include "jni.h"
#include "patate-lib.h"
#include "malloc.h"
#include "string.h"
#include "stdlib.h"

JNIEXPORT jstring JNICALL Java_com_example_kiera_tp5jni_PatateJNI_write(JNIEnv* env, jobject thiz, jstring str){
    const char *sbuild = (*env)->GetStringUTFChars(env, str, NULL);
    char *concat = malloc(strlen("Write : ") + strlen(sbuild) + 1);
    jstring ret;

    strcpy(concat, "Write : ");
    strcat(concat, sbuild);

    ret = (*env)->NewStringUTF(env, concat);

    (*env)->ReleaseStringUTFChars(env, str, sbuild);
    free(concat);

    return ret;
}

JNIEXPORT jstring JNICALL Java_com_example_kiera_tp5jni_PatateJNI_read(JNIEnv* env, jobject thiz, jstring str){
    const char *sbuild = (*env)->GetStringUTFChars(env, str, NULL);
    char *concat = malloc(strlen("Read : ") + strlen(sbuild) + 1);
    jstring ret;

    strcpy(concat, "Read : ");
    strcat(concat, sbuild);

    ret = (*env)->NewStringUTF(env, concat);

    (*env)->ReleaseStringUTFChars(env, str, sbuild);
    free(concat);

    return ret;
}

JNIEXPORT jstring JNICALL Java_com_example_kiera_tp5jni_PatateJNI_carre(JNIEnv* env, jobject thiz, jint a){
    jstring ret;
    char *concat = malloc(strlen("xx : xxxx"));
    char *sa = malloc(2* sizeof(char));
    char *sn = malloc(4* sizeof(char));

    int n = (int) a * (int) a;

    sprintf(sa, "%d", (int)a);
    sprintf(sn, "%d", n);

    strcpy(concat, sa);
    strcat(concat, " : ");
    strcat(concat, sn);
    ret = (*env)->NewStringUTF(env,concat);

    free(concat); free(sa); free(sn);
    return ret;
}

JNIEXPORT jstring JNICALL Java_com_example_kiera_tp5jni_PatateJNI_randomerror(JNIEnv* env, jobject thiz){
    jstring ret;
    int a = rand()%5;

    switch (a){
        case 0 :
            ret = (*env)->NewStringUTF(env,"Error 0 : Try to eat more potatoes and try again.");
            break;
        case 1 :
            ret = (*env)->NewStringUTF(env,"Error 42 : The ASCII answer to any question about life and universe is * (42), or whatever you want it to be. That's why.");
            break;
        case 2 :
            ret = (*env)->NewStringUTF(env,"Error 418 : I'm a teapot.");
            break;
        case 3 :
            ret = (*env)->NewStringUTF(env,"Error 619 : Keyboard not found. Press any key to continue.");
            break;
        default:
            ret = (*env)->NewStringUTF(env,"Error 20 : grade not high enough. Increase it and try again.");
    }
    return ret;
}