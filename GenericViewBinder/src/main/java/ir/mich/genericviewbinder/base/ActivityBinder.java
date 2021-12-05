package ir.mich.genericviewbinder.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import ir.mich.genericviewbinder.tools.Transfer;


public abstract class ActivityBinder<VB extends ViewBinding> extends AppCompatActivity {
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
    public Activity activity;
    protected Transfer transfer;
    protected Context context;
    protected VB binding;

    protected static void toast(CharSequence text) {
        App.toast(text);
    }

    protected static void toast_long(CharSequence text) {
        App.toast_long(text);
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
        if (getIntent().cloneFilter().getAction() != null) {
            App.main_activity_static = this;
        }
        onCreate();
    }

}
