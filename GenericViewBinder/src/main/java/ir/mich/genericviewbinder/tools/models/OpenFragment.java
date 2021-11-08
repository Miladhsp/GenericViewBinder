package ir.mich.genericviewbinder.tools.models;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.mich.genericviewbinder.tools.Transfer;

public class OpenFragment {
    public final ViewGroup layout;
    public final Fragment fragment;
    public final String tag;
    public final String addToBackStack;
    public final Transfer.Data data;

    private OpenFragment(@NonNull ViewGroup layout, @NonNull Fragment fragment,
                         @Nullable String tag, @Nullable String addToBackStack, @Nullable Transfer.Data data) {
        this.layout = layout;
        this.fragment = fragment;
        this.tag = tag;
        this.addToBackStack = addToBackStack;
        this.data = data;
    }

    public static OpenFragment builder(@NonNull ViewGroup layout, @NonNull Fragment fragment) {
        return new OpenFragment(layout, fragment, null, null, null);
    }

    public static OpenFragment builder(@NonNull ViewGroup layout, @NonNull Fragment fragment,
                                       @Nullable String tag, @Nullable String addToBackStack, @Nullable Transfer.Data data) {
        return new OpenFragment(layout, fragment, tag, addToBackStack, data);
    }
}