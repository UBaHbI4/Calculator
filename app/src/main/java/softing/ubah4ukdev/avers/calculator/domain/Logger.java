package softing.ubah4ukdev.avers.calculator.domain;

import android.util.Log;

/****
 Project Calculator
 Package softing.ubah4ukdev.avers.calculator.domain

 Created by Ivan Sheynmaer

 2021.03.18
 v1.0
 */
 public class Logger {
     public static void printLog(String message) {
         Log.d("calcLog", message);
     }

    public static void printLog(String message, LoggerType loggerType) {
         switch (loggerType) {
             case INFO:
                 Log.i("calculator", message);
                 break;
             case WARN:
                 Log.w("calculator", message);
                 break;
             case DEBUG:
                 Log.d("calculator", message);
                 break;
             case ERROR:
                 Log.e("calculator", message);
                 break;
             case ASSERT:
                 Log.wtf("calculator", message);
                 break;
         }
    }
}
