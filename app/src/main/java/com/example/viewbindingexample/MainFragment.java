package com.example.viewbindingexample;

import android.view.View;

import com.example.viewbindingexample.databinding.FragmentMainBinding;

import ir.mich.genericviewbinder.FragmentBinder;

public class MainFragment extends FragmentBinder<FragmentMainBinding> implements View.OnClickListener {


    @Override
    protected void onCreateView() {
//        android:id="@+id/example_text"
        int color = transfer.getExtras().getInt("color");
        binding.exampleText.setText("Color is " + hexColor(color));
        binding.btnTransfer.setOnClickListener(this);
        binding.root.setBackgroundColor(color);
    }

    private String hexColor(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    @Override
    public void onClick(View v) {
        transfer.startActivityForResult(
                MainActivity.class, null, result -> {
                    binding.exampleText.setText("im Back from activity");
                });
    }
}