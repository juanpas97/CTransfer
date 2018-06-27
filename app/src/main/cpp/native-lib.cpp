#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_juanperezdealgaba_ctransfer_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_juanperezdealgaba_ctransfer_MainActivity_test(JNIEnv *env, jobject instance,
                                                               jstring test) {
    const char *title = env->GetStringUTFChars(test, 0);

    char *result =(char*)malloc(sizeof(title)+ 128);

    strcpy(result,title);

    jstring result_java = env->NewStringUTF(result);
    return result_java;
}