package ir.mich.genericviewbinder.base;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

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
        return PreferenceManager.getDefaultSharedPreferences(App.getContext());
    }

    public static SharedPreferences getSharedPreferences(String name) {
        return getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferencesBase(String name, int mode) {
        return getAppContext().getSharedPreferences(name, mode);
    }

    public static Snackbar snackbar(CharSequence text, int duration) {
        return Snackbar.make(getActivity().findViewById(android.R.id.content), text, duration);
    }

    public static void snackbar_long(CharSequence text) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show();
    }

    public static void snackbar_long(CharSequence text_show,
                                     @Nullable CharSequence text_action,
                                     @Nullable final View.OnClickListener listener) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), text_show, Snackbar.LENGTH_LONG).setAction(text_action, listener).show();
    }

    public static void snackbar_short(CharSequence text) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT).show();
    }

    public static void snackbar_short(CharSequence text_show,
                                      @Nullable CharSequence text_action,
                                      @Nullable final View.OnClickListener listener) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), text_show, Snackbar.LENGTH_SHORT).setAction(text_action, listener).show();
    }

    public static void snackbar_indefinite(CharSequence text) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_INDEFINITE).show();
    }

    public static void snackbar_indefinite(CharSequence text_show,
                                           @Nullable CharSequence text_action,
                                           @Nullable final View.OnClickListener listener) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), text_show, Snackbar.LENGTH_INDEFINITE).setAction(text_action, listener).show();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static class PermissionManager {

        private static Map<Integer, ActivityResultLauncher<String[]>> requestCodes = new HashMap<>();

        public static ActivityResultLauncher<String[]> handler(int requestCode) {
            return requestCodes.get(requestCode);
        }

        public static void builder(
                int requestCode,
                @NonNull ActivityResultCallback<Map<String, Boolean>> booleanActivityResultCallback
        ) {
            requestCodes.put(
                    requestCode,
                    ((ComponentActivity) (App.getActivity())).registerForActivityResult(
                            new ActivityResultContracts.RequestMultiplePermissions()
                            , booleanActivityResultCallback)
            );
        }

    }
}