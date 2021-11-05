package com.example.viewbindingexample;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;

import com.example.viewbindingexample.databinding.ActivityMainBinding;

import java.util.Random;

import ir.mich.genericviewbinder.base.ActivityBinder;
import ir.mich.genericviewbinder.tools.models.OpenFragment;


public class MainActivity extends ActivityBinder<ActivityMainBinding> implements View.OnClickListener {

    private int i;

    private static int randomColor() {
        Random rnd = new Random();
        return Color.argb(255,
                rnd.nextInt(256),
                rnd.nextInt(256),
                rnd.nextInt(256));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate() {
//        android:id="@+id/text"
//        findViewById(R.id.text);
        binding.text.setText(" MainActivity ");
        binding.btnTransfer.setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnTransfer.getId()) {
            binding.text.setText("MainFragment");
            binding.count.setText("" + ++i);
            transfer.startFragment(OpenFragment.builder(
                    binding.redFrame,
                    new MainFragment(),
                    null,
                    null,
                    bundle -> {
                        bundle.putInt("color", randomColor());
                        return bundle;
                    }));
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        binding.count.setText("" + --i);
        if (i == 0)
            binding.text.setText("MainActivity");
    }
}
