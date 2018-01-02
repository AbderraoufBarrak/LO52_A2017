#include <jni.h>
#include <string>
#include <sstream>
#include <cstdlib>
#include <ctime>

extern "C" {
    /**
     * Fonction native read
     * Elle traite la chaîne de caractère pour l'affichage
     */
    JNIEXPORT jstring JNICALL
    Java_com_lo52_flo_lo52_1tp5_LO52MainActivity_read(JNIEnv *env, jobject instance, jstring value_) {

        std::string returnValue = "Vide";

        std::string startValue = env->GetStringUTFChars(value_, 0);
        if(!startValue.empty()){
            std::string label ("Read : ");
            returnValue = label + startValue;
        }

        return env->NewStringUTF(returnValue.c_str());
    }

    /**
    * Fonction native write
    * Elle traite la chaîne de caractère pour l'affichage
    */
    JNIEXPORT jstring JNICALL
    Java_com_lo52_flo_lo52_1tp5_LO52MainActivity_write(JNIEnv *env, jobject instance, jstring value_) {

        std::string returnValue = "Vide";

        std::string startValue = env->GetStringUTFChars(value_, 0);
        if(!startValue.empty()){
            std::string label ("Write : ");
            returnValue = label + startValue;
        }

        return env->NewStringUTF(returnValue.c_str());
    }

    /**
     * Fonction native stop
     * Elle traite la chaîne de caractère pour l'affichage
     */
    JNIEXPORT jstring JNICALL
    Java_com_lo52_flo_lo52_1tp5_LO52MainActivity_stop(JNIEnv *env, jobject instance, jint value) {

        int multiple = value*value;
        std::ostringstream oss;
        oss << value << "/" << multiple;
        std::string tmp = oss.str();

        return (env)->NewStringUTF(tmp.c_str());

    }

    /**
     * Fonction native start
     * Elle traite la chaîne de caractère pour l'affichage
     */
    JNIEXPORT jstring JNICALL
    Java_com_lo52_flo_lo52_1tp5_LO52MainActivity_start(JNIEnv *env, jobject instance, jint value) {

        int multiple = value*value;
        std::ostringstream oss;
        oss << value << "/" << multiple;
        std::string tmp = oss.str();

        return (env)->NewStringUTF(tmp.c_str());

    }

    /**
     * Fonction native reset
     * Elle traite la chaîne renvoie la correspondance des codes d'erreurs
     */
    JNIEXPORT jstring JNICALL
    Java_com_lo52_flo_lo52_1tp5_LO52MainActivity_reset(JNIEnv *env, jobject instance) {

        std::string returnValue = "";
        srand(time(NULL));

        switch (rand()%3){

            case 0 :
                returnValue = "0 : Succès";
                break;
            case 1 :
                returnValue = "1 : Problème de connexion";
                break;
            case 2 :
                returnValue = "2 : Commande inconnue";
                break;
            default:
                returnValue = "Code inconnue";
        }

        return (env)->NewStringUTF(returnValue.c_str());
    }
}
