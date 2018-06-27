## CTransfer

This tool is only use to measure how much time does a string need to be sent to native code through
the Android NDK, copied into another string and returned it back to the Java code.

The length of the script is decided in the code. If some modification is needed, we need to change
the length of the random string in MainActivity.java

 ```
String test = string.generateRandomString(128); 
```

and the length of the script in the native-lib.cpp
 
 ```
char *result =(char*)malloc(sizeof(title)+ 128);
```

Also, if you want, change the name of the .txt file that will be generated.

