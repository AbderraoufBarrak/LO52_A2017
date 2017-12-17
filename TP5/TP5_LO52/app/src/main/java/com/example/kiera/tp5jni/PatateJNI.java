package com.example.kiera.tp5jni;

/**
 * Created by kiera on 15/12/2017.
 */

public class PatateJNI {

    static native String write(String a);

    static native String read(String a);

    static native String carre(int a);

    static native String randomerror();

    static {
        System.loadLibrary("patate-lib");
    }
}
