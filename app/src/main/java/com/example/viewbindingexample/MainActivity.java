package com.example.viewbindingexample;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.viewbindingexample.databinding.ActivityMainBinding;

import java.util.Random;

import ir.mich.genericviewbinder.ActivityBinder;


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
        binding.text.setText(" MainActivity ");
        binding.btnTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btnTransfer.getId()) {
            binding.text.setText("MainFragment");
            binding.count.setText("" + ++i);
            Bundle bundle = new Bundle();
            bundle.putInt("color", randomColor());
            transfer.startFragment(
                    binding.redFrame,
                    new MainFragment(),
                    null,
                    null,
                    bundle);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        binding.count.setText("" + --i);
        if (i == 0)
            binding.text.setText("MainActivity");
    }
}
