/*
package ir.mich.genericviewbinder.tools;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static ir.mich.genericviewbinder.base.App.getActivity;
import static ir.mich.genericviewbinder.base.App.registerForPermissionsResult;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions;
import androidx.annotation.ChecksSdkIntAtLeast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Map;

import ir.mich.genericviewbinder.base.App;

public class PermissionManager {
    public static void request(
            int requestCode,
            String... permissions
    ) {
        if (checkAbilityOfRequestRunTimePermissions()) {
            if (!isPermissionNotGranted(permissions)) {
                if (shouldShowRequestPermissionRationale(permissions)) {
                    App.snackbar_indefinite(
                            "Please Grant Permissions.",
                            "ENABLE",
                            v -> requestPermissions(getActivity(), permissions, requestCode)
                    );
                } else {
                    requestPermissions(getActivity(), permissions, requestCode);
                }
            }
        }
    }

    public static void request(
            int requestCode,
            @Nullable OnPermissionsGranted onPermissionsGranted,
            OnRequestPermissions onRequestPermissions,
            String... permissions
    ) {
        if (checkAbilityOfRequestRunTimePermissions()) {
            if (isPermissionNotGranted(permissions)) {
                if (shouldShowRequestPermissionRationale(permissions)) {
                    onRequestPermissions.onRequest(new RequestPermission() {
                        @Override
                        public void apply() {
                            requestPermissions(getActivity(), permissions, requestCode);
                        }
                    });
                } else {
                    requestPermissions(getActivity(), permissions, requestCode);
                }
            } else {
                if (onPermissionsGranted != null)
                    onPermissionsGranted.onGranted();
            }
        }
    }

    private static boolean isPermissionNotGranted(String[] permissions) {
        int sum = 0;
        for (String p : permissions) {
            sum += checkSelfPermission(p);
        }
        return sum != PackageManager.PERMISSION_GRANTED;
    }

    private static boolean shouldShowRequestPermissionRationale(String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permission);
    }

    private static boolean shouldShowRequestPermissionRationale(String[] permissions) {
        boolean flag = false;
        for (String p : permissions) {
            flag = flag || shouldShowRequestPermissionRationale(p);
        }
        return flag;
    }

    private static int checkSelfPermission(String permission) {
        return ContextCompat.checkSelfPermission(getActivity(), permission);
    }

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
    private static boolean checkAbilityOfRequestRunTimePermissions() {
        return Build.VERSION.SDK_INT >= 23;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void asd() {
        ActivityResultLauncher<String[]> mPermissionResult;
        mPermissionResult = registerForPermissionsResult(result -> {});
        // Launch the permission window -- this is in onCreateView()
        mPermissionResult.launch(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION});
    }



    public interface OnRequestPermissions {
        void onRequest(RequestPermission requestPermission);
    }

    public interface OnPermissionsGranted {
        void onGranted();
    }

    public abstract static class RequestPermission {
        public abstract void apply();
    }

}
*/
