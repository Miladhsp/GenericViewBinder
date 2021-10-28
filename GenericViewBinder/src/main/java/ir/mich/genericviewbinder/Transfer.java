package ir.mich.genericviewbinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class Transfer {

    private final FragmentActivity activity;
    private final Context context;
    private Fragment fragment = null;

    public Transfer(AppCompatActivity activity) {
        this.activity = activity;
        context = activity;
    }

    public Transfer(Fragment fragment) {
        this.activity = fragment.getActivity();
        context = fragment.getActivity();
        this.fragment = fragment;
    }

    public void startActivity(Class<?> clazz, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public void startFragment(ViewGroup layout, SupperFragment<?> fragment, @Nullable String tag, @Nullable String addToBackStack, @Nullable Bundle bundle) {
        fragment.setArguments((bundle == null) ? new Bundle() : bundle);
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(layout.getId(), fragment, tag)
                .addToBackStack(addToBackStack)
                .commit();
    }

    public void startFrgmentForResult(int requestCode, int resultCode, ViewGroup layout, SupperFragment<?> fragment, String tag, String addToBackStack, @NonNull Bundle bundle) {
        startFragment(layout, fragment, tag, addToBackStack, null);
        fragment.setupResult(requestCode, resultCode, bundle);
    }

    protected Bundle getExtras() {
        if (fragment == null) {
            return activity.getIntent().getExtras();
        } else {
            return fragment.getArguments();
        }
    }

}
