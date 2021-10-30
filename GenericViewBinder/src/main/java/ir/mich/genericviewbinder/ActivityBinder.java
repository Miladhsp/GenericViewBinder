package ir.mich.genericviewbinder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;


public abstract class ActivityBinder<VB extends ViewBinding> extends AppCompatActivity {
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
    @SuppressLint("StaticFieldLeak")
    public static Activity activity_static = App.getActivity();
    @SuppressLint("StaticFieldLeak")
    public static Context context_static = App.getContext();
    public Activity activity;
    protected Transfer transfer;
    protected Context context;
    protected Bundle args;
    protected VB binding;

    protected static void toast(CharSequence text) {
        App.toast(0, text);
    }

    protected static void toast_long(CharSequence text) {
        App.toast(1, text);
    }

    protected abstract void onCreate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = new GenericBinder<VB>(this, 0).inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        activity = this;
        transfer = new Transfer(this);
        args = transfer.getExtras();
        if (getIntent().cloneFilter().getAction() != null) {
            App.activity_static = this;
        }
        init();
        onCreate();
    }

    protected void init() {
    }
}
