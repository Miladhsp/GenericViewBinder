package ir.mich.genericviewbinder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class FragmentBinder<VB extends ViewBinding> extends Fragment {
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
    public static final Activity activity_main = App.getActivity();
    @SuppressLint("StaticFieldLeak")
    public static final Context context_main = App.getContext();
    public Bundle args;
    public View view;
    public Activity activity;
    protected Transfer transfer;
    protected Context context;
    protected VB binding;

    protected static void toast(CharSequence text) {
        App.toast(0, text);
    }

    protected static void toast_long(CharSequence text) {
        App.toast(1, text);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = new GenericBinder<VB>(this, 0).inflate(inflater, container);
        view = binding.getRoot();
        context = binding.getRoot().getContext();
        activity = (Activity) context;
        transfer = new Transfer(this);
        args = transfer.getExtras();
        init();
        onCreateView();
        return view;
    }

    protected void init() {
    }

    protected abstract void onCreateView();
}