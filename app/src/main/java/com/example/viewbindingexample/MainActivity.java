package com.example.viewbindingexample;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.example.viewbindingexample.databinding.ActivityMainBinding;

import ir.mich.genericviewbinder.base.ActivityBinder;
import ir.mich.genericviewbinder.base.App;
import ir.mich.genericviewbinder.tools.Colors;
import ir.mich.genericviewbinder.tools.PermissionManager;
import ir.mich.genericviewbinder.tools.RunOnce;
import ir.mich.genericviewbinder.tools.models.OpenFragment;


public class MainActivity extends ActivityBinder<ActivityMainBinding> implements View.OnClickListener {

    private static int activityCounter;
    private int fragmentCounter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate() {
//        android:id="@+id/text"
//        findViewById(R.id.text);
        binding.text.setText(" MainActivity ");
        binding.btnTransfer.setOnClickListener(this);
        RunOnce.FirstInstall.init("First", new RunOnce.FirstTimeListener() {
            @Override
            public void onFirstTime() {
                toast("onFirstTime ✔");
            }

            @Override
            public void onNotFirstTime() {
                toast("onNotFirstTime ✔");
            }
        });
        activityCounter++;
        ///////////////////////////
        requestPermission();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void requestPermission() {
        PermissionManager.builder(
                123,
                result -> result.forEach(
                        (permission, isGranted) -> {
                            switch (permission) {
                                case Manifest.permission.ACCESS_FINE_LOCATION:
                                    App.snackbar_indefinite(
                                            "ACCESS_LOCATION : " + isGranted,
                                            "OK",
                                            view -> {
                                            });
                                    break;
                                case Manifest.permission.CAMERA:
                                    toast("ACCESS_CAMERA: " + isGranted);
                                    break;
                            }
                        }
                ));
        PermissionManager.handler(123).launch(
                new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CAMERA
                }
        );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnTransfer.getId()) {
            binding.text.setText("MainFragment");
            binding.count.setText("" + ++fragmentCounter);
            transfer.startFragment(OpenFragment.builder(
                    binding.redFrame,
                    new MainFragment(),
                    null,
                    null,
                    bundle -> {
                        bundle.putInt("color", Colors.random());
                        return bundle;
                    }));
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        binding.count.setText("" + --fragmentCounter);
        if (fragmentCounter == 0) {
            binding.text.setText("MainActivity");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityCounter-- == 1) {
            App.forceStop();
        }
    }

}
