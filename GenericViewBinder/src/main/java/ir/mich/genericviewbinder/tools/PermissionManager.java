package ir.mich.genericviewbinder.tools;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import ir.mich.genericviewbinder.base.App;

public class PermissionManager {

    private static final Map<Integer, ActivityResultLauncher<String[]>> requestCodes = new HashMap<>();

    public static ActivityResultLauncher<String[]> launcher(int requestCode) {
        return requestCodes.get(requestCode);
    }

    public static void handler(
            int requestCode,
            Functions.Void._2<String, Boolean> action
    ) {
        handler(requestCode, result -> Tools.forEach(result, action));
    }

    public static void handler(
            int requestCode,
            @NonNull ActivityResultCallback<Map<String, Boolean>> booleanActivityResultCallback
    ) {
        requestCodes.put(
                requestCode,
                ((ComponentActivity) (App.getActivity()))
                        .registerForActivityResult(
                                new ActivityResultContracts.RequestMultiplePermissions()
                                , booleanActivityResultCallback
                        )
        );
    }

}