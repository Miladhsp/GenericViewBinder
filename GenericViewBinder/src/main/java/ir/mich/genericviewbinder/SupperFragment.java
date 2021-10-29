package ir.mich.genericviewbinder;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

public abstract class SupperFragment<VB extends ViewBinding> extends Fragment {
    public Bundle args;
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
    protected Context context_static = App.getContext();
    protected Transfer transfer;
    protected Context context;
    protected VB binding;
    private int requestCode;
    private int resultCode;
    private Bundle result;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = new GenericBinder<VB>(this, 0).inflate(inflater, container);
        context = binding.getRoot().getContext();
        transfer = new Transfer(this);
        args = transfer.getExtras();
        init();
        onCreateView();
        return binding.getRoot();
    }

    protected void init() {
    }

    protected abstract void onCreateView();

    public void setupResult(int requestCode, int resultCode, Bundle data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.result = data;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onFragmentResult(requestCode, resultCode, result);
    }

    protected void onFragmentResult(int requestCode, int resultCode, Bundle data) {
    }

    protected void toast(CharSequence text) {
        toast(0, text);
    }

    protected void toast_long(CharSequence text) {
        toast(1, text);
    }

    private void toast(int duration, CharSequence text) {
        Toast.makeText(context, text, (duration == 0) ? LENGTH_SHORT : LENGTH_LONG).show();
    }
}