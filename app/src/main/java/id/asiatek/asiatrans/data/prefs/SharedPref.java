package id.asiatek.asiatrans.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import id.asiatek.asiatrans.AppLoader;

/**
 * Created by knalb on 11/07/18.
 */

public class SharedPref {

    private static final String TAG = "SharedPref";
    public static final String token = "token";
    public static final String firstTime = "firstTime";
    public static final String firstTimeMain = "firstTimeMain";

    private static SharedPreferences getPref() {
        Context context = AppLoader.appContext;
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void deleteToken() {
        getPref().edit()
                .remove(token)
                .apply();
    }

    public static void setToken(String value) {
        getPref().edit()
                .putString(token, value)
                .apply();
    }

    public static void setFirstTime(Boolean value) {
        getPref().edit()
                .putBoolean(firstTime, value)
                .apply();
    }

    public static void setFirstTimeMain(Boolean value) {
        getPref().edit()
                .putBoolean(firstTimeMain, value)
                .apply();
    }

    public static String getToken() {return getPref().getString(token, "");
    }

    public static Boolean getFirstTime() {
        return getPref().getBoolean(firstTime, true);
    }

    public static Boolean getFirstTimeMain() {
        return getPref().getBoolean(firstTimeMain, true);
    }
}
