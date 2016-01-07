package demo.kolorob.kolorobdemoversion.utils;

import android.util.Log;

/**
 * Created by touhid on 10/30/15.
 *
 * @author touhid
 */
public class Lg {
    private static final boolean IS_APP_DEBUGGABLE = true;

    public static void d(String tag, String msg) {
        if (IS_APP_DEBUGGABLE)
            Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (IS_APP_DEBUGGABLE)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (IS_APP_DEBUGGABLE)
            Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (IS_APP_DEBUGGABLE)
            Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (IS_APP_DEBUGGABLE)
            Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        if (IS_APP_DEBUGGABLE)
            Log.e(tag, msg, e);
    }
}
