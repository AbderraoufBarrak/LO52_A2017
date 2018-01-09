#include <jni.h>
#include <string.h>
#include <string>
#include <stdlib.h>


extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_tp5jniapplicationbarrak_LO52MainActivity_stringFromJNIReset(JNIEnv *env, jobject instance) {
    char *result;
    jint r = rand() % 2;
    if (r==0){
        result = (char *) malloc(strlen("Succès") + 1);
        strcpy(result, "Succès");
    }

    else
    {
        result = (char *) malloc(strlen("Echec") + 1);
        strcpy(result, "Echec");
    }

    jstring retval = (*env).NewStringUTF(result);
    free(result);
    return retval;

}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_tp5jniapplicationbarrak_LO52MainActivity_stringFromJNIStart(
        JNIEnv *env,
        jobject, /* this */
        jint nombre) {

    jint CarreNombre = nombre*nombre;

    //Convertir le carré en un jstring
    char bufCarreNombre[10];
    sprintf(bufCarreNombre, "%d", CarreNombre);  // error checking omitted
    jstring jStringCarre = (*env).NewStringUTF(bufCarreNombre);
    //Le convertir en un char *
    const char *strCarre= (*env).GetStringUTFChars(jStringCarre,0);

    //Convertir le nombre en un jstring
    char bufNombre[10];
    sprintf(bufNombre, "%d", nombre);  // error checking omitted
    jstring jStringNombre = (*env).NewStringUTF(bufNombre);
    //Le convertir en char *
    const char *strNombre= (*env).GetStringUTFChars(jStringNombre,0);

    //Concaténer les deux
    char *concatenated;
    concatenated = (char *) malloc(strlen(strNombre) + strlen("/") + strlen(strCarre) + 1);
    strcpy(concatenated, strNombre);
    strcat(concatenated, "/");
    strcat(concatenated, strCarre);

    /* Create java string from our concatenated C string */
    jstring retval = (*env).NewStringUTF(concatenated);

    //need to release this string when done with it in order to
    //avoid memory leak
    (*env).ReleaseStringUTFChars(jStringNombre,strNombre);
    (*env).ReleaseStringUTFChars(jStringCarre,strCarre);
    /* Free the memory in concatenated */
    free(concatenated);
    return retval;

}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_tp5jniapplicationbarrak_LO52MainActivity_stringFromJNIStop(
        JNIEnv *env,
        jobject, /* this */
        jint nombre) {

    jint CarreNombre = nombre*nombre;

    //Convertir le carré en un jstring
    char bufCarreNombre[10];
    sprintf(bufCarreNombre, "%d", CarreNombre);  // error checking omitted
    jstring jStringCarre = (*env).NewStringUTF(bufCarreNombre);
    //Le convertir en un char *
    const char *strCarre= (*env).GetStringUTFChars(jStringCarre,0);

    //Convertir le nombre en un jstring
    char bufNombre[10];
    sprintf(bufNombre, "%d", nombre);  // error checking omitted
    jstring jStringNombre = (*env).NewStringUTF(bufNombre);
    //Le convertir en char *
    const char *strNombre= (*env).GetStringUTFChars(jStringNombre,0);

    //Concaténer les deux
    char *concatenated;
    concatenated = (char *) malloc(strlen(strNombre) + strlen("/") + strlen(strCarre) + 1);
    strcpy(concatenated, strNombre);
    strcat(concatenated, "/");
    strcat(concatenated, strCarre);

    /* Create java string from our concatenated C string */
    jstring retval = (*env).NewStringUTF(concatenated);

    //need to release this string when done with it in order to
    //avoid memory leak
    (*env).ReleaseStringUTFChars(jStringNombre,strNombre);
    (*env).ReleaseStringUTFChars(jStringCarre,strCarre);
    /* Free the memory in concatenated */
    free(concatenated);
    return retval;

}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_tp5jniapplicationbarrak_LO52MainActivity_stringFromJNIWrite(
        JNIEnv *env,
        jobject, /* this */
        jstring text) {
    const char *str= (*env).GetStringUTFChars(text,0);
    //printf("%s", str);
    char *concatenated;
    concatenated = (char *) malloc(strlen("Write ") + strlen(str) + 1);
    strcpy(concatenated, "Write ");
    strcat(concatenated, str);

    /* Create java string from our concatenated C string */
    jstring retval = (*env).NewStringUTF(concatenated);

    //need to release this string when done with it in order to
    //avoid memory leak
    (*env).ReleaseStringUTFChars(text,str);
    /* Free the memory in concatenated */
    free(concatenated);
    return retval;
}

extern "C"
JNIEXPORT jstring JNICALL
Java_fr_utbm_tp5jniapplicationbarrak_LO52MainActivity_stringFromJNIRead(
        JNIEnv *env,
        jobject, /* this */
        jstring text) {

    //jstring hello = text;

    const char *str= (*env).GetStringUTFChars(text,0);
    //printf("%s", str);
    char *concatenated;
    concatenated = (char *) malloc(strlen("Read ") + strlen(str) + 1);
    strcpy(concatenated, "Read ");
    strcat(concatenated, str);

    /* Create java string from our concatenated C string */
    jstring retval = (*env).NewStringUTF(concatenated);

    //need to release this string when done with it in order to
    //avoid memory leak
    (*env).ReleaseStringUTFChars(text,str);
    /* Free the memory in concatenated */
    free(concatenated);
    return retval;
}

