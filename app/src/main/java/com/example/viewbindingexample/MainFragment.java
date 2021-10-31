package com.example.viewbindingexample;

import com.example.viewbindingexample.databinding.FragmentMainBinding;

import ir.mich.genericviewbinder.FragmentBinder;

public class MainFragment extends FragmentBinder<FragmentMainBinding> {

    @Override
    protected void onCreateView() {
//        android:id="@+id/example_text"
        int color = transfer.getExtras().getInt("color");
        binding.exampleText.setText("Color is " + hexColor(color));
        binding.root.setBackgroundColor(color);
    }

    private String hexColor(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }
}