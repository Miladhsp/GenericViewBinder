package com.example.viewbindingexample;

import android.annotation.SuppressLint;
import android.view.View;

import com.example.viewbindingexample.databinding.FragmentMainBinding;

import ir.mich.genericviewbinder.base.FragmentBinder;

public class MainFragment extends FragmentBinder<FragmentMainBinding> implements View.OnClickListener {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreateView() {
        int color = transfer.getExtras().getInt("color");
//        android:id="@+id/example_text"
//        view.findViewById(R.id.example_text);
        binding.exampleText.setText("Color is " + hexColor(color));
        binding.btnTransfer.setOnClickListener(this);
        binding.root.setBackgroundColor(color);
    }

    private String hexColor(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        transfer.startActivityForResult(
                MainActivity.class, null, result -> {
                    binding.exampleText.setText("im Back from activity");
                    toast("im Back from activity");
                });
    }
}