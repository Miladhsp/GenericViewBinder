package ir.mich.genericviewbinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentResultListener;

public class Transfer {

    protected final String SET_FRAGMENT_RESULT_LISTENER = "SET_FRAGMENT_RESULT_LISTENER";
    private final FragmentActivity activity;
    private final Context context;
    private ActivityResultBinder<Intent, ActivityResult> activityLauncher;
    private Fragment fragment = null;

    public Transfer(AppCompatActivity activity) {
        this.activity = activity;
        context = activity;
        activityLauncher = ActivityResultBinder.registerActivityForResult(activity);
    }

    public Transfer(Fragment fragment) {
        this.activity = fragment.getActivity();
        context = fragment.getActivity();
        this.fragment = fragment;
        activityLauncher = ActivityResultBinder.registerActivityForResult(fragment);
    }

    public void startActivity(Class<?> clazz, @Nullable Data data) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(validData(data));
        context.startActivity(intent);
    }

    public void startFragment(
            ViewGroup layout, FragmentBinder<?> fragment,
            @Nullable String tag, @Nullable String addToBackStack, Data data) {
        fragment.setArguments(validData(data));
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(layout.getId(), fragment, tag)
                .addToBackStack(addToBackStack)
                .commit();
    }

    public void startFragmentForResult(
            OpenFragment fragment, String requestKey, FragmentResultListener listener) {
        startFragment(fragment.layout, fragment.fragment, fragment.tag,
                fragment.addToBackStack, fragment.data);
        if (this.fragment == null) {
            Protected aProtected = new Protected(SET_FRAGMENT_RESULT_LISTENER, () -> {
                activity.getSupportFragmentManager()
                        .setFragmentResultListener(requestKey,
                                fragment.fragment.getViewLifecycleOwner(), listener);
            });
            Access.updateField(FragmentBinder.class, ((FragmentBinder<?>) fragment.fragment),
                    "resultManager", aProtected);
        } else {
            this.fragment.requireActivity().getSupportFragmentManager()
                    .setFragmentResultListener(requestKey, this.fragment.getViewLifecycleOwner(), listener);
        }
    }

    public void finishFragmentForResult(String requestKey, Data data) {
        fragment.requireActivity().getSupportFragmentManager()
                .setFragmentResult(requestKey, validData(data));
        //fragment.requireActivity().getFragmentManager().popBackStack();
        App.getActivity().onBackPressed();
    }

    public Bundle getExtras() {
        if (fragment == null) {
            return activity.getIntent().getExtras();
        } else {
            return fragment.getArguments();
        }
    }

    public void startActivityForResult(
            Class<?> cls, @Nullable Data data,
            @Nullable ActivityResultBinder.OnActivityResult<ActivityResult> onActivityResult) {
        Intent intent = new Intent(context, cls);
        intent.putExtras(validData(data));
        activityLauncher.launch(intent, onActivityResult);
    }

    public void finishActivityForResult(ir.mich.genericviewbinder.Transfer.ResultActivity result) {
        result.finish(activity::setResult);
        activity.finish();
    }

    private Bundle validData(Data data) {
        Bundle bundle = new Bundle();
        if (data != null) {
            Bundle temp = data.set(bundle);
            if (temp != null) {
                return temp;
            }
        }
        return bundle;
    }

    /**
     * Call reply.setResult to set the result that your activity will return to its caller.
     * {@link android.app.Activity.RESULT_CANCELED}
     * {@link android.app.Activity.RESULT_OK}
     * {@link android.app.Activity.RESULT_FIRST_USER}
     */
    public interface ResultActivity {
        public void finish(Reply reply);
    }

    public interface Reply {
        void setResult(int resultCode, Intent intent);
    }

    public interface Data {
        Bundle set(Bundle bundle);
    }
}
