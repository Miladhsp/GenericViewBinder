package ir.mich.genericviewbinder;

import android.app.Application;
import android.content.Context;

/**Do this:
 *
 * In the Android Manifest file, declare the following.
 *
 * <application
 * ...
 * android:name="ir.mich.genericviewbinder.App"
 * >
 * </application>
 *
 */
public class App extends Application {

    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
}
