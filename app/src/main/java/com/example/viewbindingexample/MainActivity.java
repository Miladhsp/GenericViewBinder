package com.example.viewbindingexample;

import android.annotation.SuppressLint;

import com.example.viewbindingexample.databinding.ActivityMainBinding;

import ir.mich.genericviewbinder.ActivityBinder;


public class MainActivity extends ActivityBinder<ActivityMainBinding> {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate() {
        binding.hello.setText("Yes");
    }

}