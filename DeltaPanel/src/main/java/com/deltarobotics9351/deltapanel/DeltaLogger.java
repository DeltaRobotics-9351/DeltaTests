package com.deltarobotics9351.deltapanel;

import android.util.Log;

public class DeltaLogger {

    public enum LogLevel{ INFO, DEBUG, WARNING, ERROR, SEVERE }

    public static void log(LogLevel level, String message){
        switch(level){
            case INFO:
                Log.println(Log.INFO, "DeltaPanel", message);
                break;
            case DEBUG:
                Log.println(Log.DEBUG, "DeltaPanel", message);
                break;
            case WARNING:
                Log.println(Log.WARN, "DeltaPanel", message);
                break;
            case ERROR:
                Log.println(Log.ERROR, "DeltaPanel", message);
                break;
            case SEVERE:
                Log.println(Log.ERROR, "DeltaPanel", "FATAL!: "+message);
                break;
            default:
                Log.println(Log.INFO, "DeltaPanel", "UNKNOWN" + message);
                break;
        }
    }

    public static void logInfo(String message){ log(LogLevel.INFO, message); }

    public static void logDebug(String message){ log(LogLevel.DEBUG, message); }

    public static void logError(String message){ log(LogLevel.ERROR, message); }

    public static void logWarning(String message){ log(LogLevel.WARNING, message); }

    public static void logSevere(String message){ log(LogLevel.SEVERE, message); }

}
