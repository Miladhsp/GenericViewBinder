package ir.mich.genericviewbinder.base;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

/**
 * Do this:
 * <p>
 * In the Android Manifest file, declare the following.
 * <p>
 * <application
 * ...
 * android:name="ir.mich.genericviewbinder.base.App"
 * >
 * </application>
 */
public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    protected static Activity main_activity_static;
    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public static Activity getActivity() {
        return main_activity_static;
    }

    public static Context getContext() {
        return main_activity_static;
    }

    public static Context getAppContext() {
        return getApplication().getApplicationContext();
    }

    public static void forceStop() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void toast(CharSequence text) {
        Toast.makeText(getContext(), text, LENGTH_SHORT).show();
    }

    public static void toast_long(CharSequence text) {
        Toast.makeText(getContext(), text, LENGTH_LONG).show();
    }

    public static SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    public static SharedPreferences getSharedPreferences(String name) {
        return getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferencesBase(String name, int mode) {
        return getAppContext().getSharedPreferences(name, mode);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

}