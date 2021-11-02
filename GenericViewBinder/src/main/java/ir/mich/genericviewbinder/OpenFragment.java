package ir.mich.genericviewbinder;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class OpenFragment {
    public final ViewGroup layout;
    public final FragmentBinder<?> fragment;
    public final String tag;
    public final String addToBackStack;
    public final Transfer.Data data;

    public OpenFragment(ViewGroup layout, FragmentBinder<?> fragment) {
        this.layout = layout;
        this.fragment = fragment;
        this.tag = null;
        this.addToBackStack = null;
        this.data = null;
    }

    public OpenFragment(@NonNull ViewGroup layout, @NonNull FragmentBinder<?> fragment,
                        @Nullable String tag, @Nullable String addToBackStack, @Nullable Transfer.Data data) {
        this.layout = layout;
        this.fragment = fragment;
        this.tag = tag;
        this.addToBackStack = addToBackStack;
        this.data = data;
    }
}