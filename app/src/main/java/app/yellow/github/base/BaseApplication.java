package app.yellow.github.base;

import android.content.Context;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

public class BaseApplication extends LitePalApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        super.onCreate();
        LitePal.initialize(this);
    }

    public static Context getmContext() {
        return mContext;
    }
}
