package com.flurry.android;

import android.util.Log;

/* loaded from: classes.dex */
public final class bm {
    private static boolean a = false;
    private static int b = 5;

    bm() {
    }

    public static int a(String str, String str2) {
        if (a || b > 3) {
            return 0;
        }
        return Log.d(str, str2);
    }

    public static int a(String str, String str2, Throwable th) {
        if (a || b > 3) {
            return 0;
        }
        return Log.d(str, str2, th);
    }

    public static void a() {
        a = true;
    }

    public static void a(int i) {
        b = i;
    }

    public static boolean a(String str) {
        return Log.isLoggable(str, 3);
    }

    public static int b(String str, String str2) {
        if (a || b > 6) {
            return 0;
        }
        return Log.e(str, str2);
    }

    public static int b(String str, String str2, Throwable th) {
        if (a || b > 6) {
            return 0;
        }
        return Log.e(str, str2, th);
    }

    public static void b() {
        a = false;
    }

    public static int c(String str, String str2) {
        if (a || b > 4) {
            return 0;
        }
        return Log.i(str, str2);
    }

    public static int d(String str, String str2) {
        if (a || b > 5) {
            return 0;
        }
        return Log.w(str, str2);
    }
}
