package ir.mich.genericviewbinder.base;

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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewbinding.ViewBinding;

import ir.mich.genericviewbinder.tools.Secretary;
import ir.mich.genericviewbinder.tools.Transfer;

public abstract class FragmentBinder<VB extends ViewBinding> extends Fragment {
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
    private final Secretary resultManager = null;
    public View view;
    public Activity activity;
    public FragmentActivity activity_require;
    protected Transfer transfer;
    protected Context context;
    protected VB binding;

    protected static void toast(CharSequence text) {
        App.toast(text);
    }

    protected static void toast_long(CharSequence text) {
        App.toast_long(text);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = new GenericBinder<VB>(this, 0).inflate(inflater, container);
        view = binding.getRoot();
        context = binding.getRoot().getContext();
        activity = (Activity) context;
        activity_require = requireActivity();
        transfer = new Transfer(this);
        onCreateView();
        return view;
    }

    protected abstract void onCreateView();

    @SuppressWarnings("null")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Secretary.invoke(resultManager);
    }
}