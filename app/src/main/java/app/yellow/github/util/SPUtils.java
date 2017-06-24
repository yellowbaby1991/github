package app.yellow.github.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtils {

    private static SharedPreferences sSp;

    private static SharedPreferences getInstance(Context context) {
        if (sSp == null) {
            sSp = context.getSharedPreferences(Constants.SP_FILE_NAME, Context.MODE_PRIVATE);
        }
        return sSp;
    }

    public static void putBoolean(Context context, String key, boolean values) {
        getInstance(context).edit().putBoolean(key, values).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getInstance(context).getBoolean(key, defValue);
    }

    public static void putString(Context context, String key, String values) {
        getInstance(context).edit().putString(key, values).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        return getInstance(context).getString(key, defValue);
    }

    public static void putInteger(Context context, String key, Integer values) {
        getInstance(context).edit().putInt(key, values).commit();
    }

    public static int getInteger(Context context, String key, Integer defValue) {
        return getInstance(context).getInt(key, defValue);
    }


}