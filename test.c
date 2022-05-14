#include <jni.h>
#include "test.h"
#include <stdio.h>

JNIEXPORT void JNICALL
Java_test_hello (JNIEnv *, jobject){
    printf("!!! Jesuraj Welcome to C Again !!!");
    return;
}
