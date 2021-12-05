package com.example.viewbindingexample;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.viewbindingexample.databinding.ActivityMainBinding;

import ir.mich.genericviewbinder.base.ActivityBinder;
import ir.mich.genericviewbinder.base.App;
import ir.mich.genericviewbinder.tools.Colors;
import ir.mich.genericviewbinder.tools.RunOnce;
import ir.mich.genericviewbinder.tools.models.OpenFragment;


public class MainActivity extends ActivityBinder<ActivityMainBinding> implements View.OnClickListener {

    private static int activityCounter;
    private int fragmentCounter;

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
                toast("Welcome");
            }

            @Override
            public void onNotFirstTime() {
                toast("✔");
            }
        });
        activityCounter++;
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
