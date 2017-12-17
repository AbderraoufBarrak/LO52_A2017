//
// Created by kiera on 15/12/2017.
//

#ifndef TP5_LO52_PATATE_LIB_H
#define TP5_LO52_PATATE_LIB_H

JNIEXPORT jstring JNICALL Java_com_example_kiera_tp5jni_PatateJNI_write(JNIEnv* env, jobject thiz, jstring str);
JNIEXPORT jstring JNICALL Java_com_example_kiera_tp5jni_PatateJNI_read(JNIEnv* env, jobject thiz, jstring str);
JNIEXPORT jstring JNICALL Java_com_example_kiera_tp5jni_PatateJNI_carre(JNIEnv* env, jobject thiz, jint a);
JNIEXPORT jstring JNICALL Java_com_example_kiera_tp5jni_PatateJNI_randomerror(JNIEnv* env, jobject thiz);

#endif //TP5_LO52_PATATE_LIB_H
