package ir.mich.genericviewbinder;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.Toast;

/**
 * Do this:
 * <p>
 * In the Android Manifest file, declare the following.
 * <p>
 * <application
 * ...
 * android:name="ir.mich.genericviewbinder.App"
 * >
 * </application>
 */
public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Activity activity_static;

    @SuppressLint("StaticFieldLeak")
    public static Context context_static;

    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public static Activity getActivity() {
        return (Activity) getApplication().getApplicationContext();
    }
    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    public static void toast(CharSequence text) {
        App.toast(0, text);
    }

    public static void toast_long(CharSequence text) {
        App.toast(1, text);
    }

    public static void toast(int duration, CharSequence text) {
        Toast.makeText(getContext(), text, (duration == 0) ? LENGTH_SHORT : LENGTH_LONG).show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        activity_static = getActivity();
        context_static = getContext();

    }
}
