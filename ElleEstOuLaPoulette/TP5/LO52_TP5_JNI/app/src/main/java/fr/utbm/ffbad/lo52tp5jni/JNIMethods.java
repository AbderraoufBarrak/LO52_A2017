package fr.utbm.ffbad.lo52tp5jni;

/**
 * Created by Antoine on 22/12/2017.
 */

public class JNIMethods {

    public static native String read(String str);
    public static native String write(String str);
    public static native int square(int number);
    public static native String errorCode();


    static{
        System.loadLibrary("native-lib");
    }

}