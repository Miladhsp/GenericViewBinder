package com.example.viewbindingexample;

import android.annotation.SuppressLint;

import com.example.viewbindingexample.databinding.ActivityMainBinding;

import ir.mich.genericviewbinder.ActivityBinder;
import ir.mich.genericviewbinder.App;


public class MainActivity extends ActivityBinder<ActivityMainBinding> {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate() {
        binding.hello.setText("Yes");
        App.<MainActivity>getAppCompatActivity().toast();
    }

    public void toast() {
        App.toast("Yes");
    }

}
