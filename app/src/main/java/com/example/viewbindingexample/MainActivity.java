package com.example.viewbindingexample;

import com.example.viewbindingexample.databinding.ActivityMainBinding;

import ir.mich.genericviewbinder.SupperActivity;

public class MainActivity extends SupperActivity<ActivityMainBinding> {

    @Override
    protected void onCreate() {
        binding.hello.setText("YES");
    }

}