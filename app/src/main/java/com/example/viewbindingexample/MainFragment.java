package com.example.viewbindingexample;

import android.os.Bundle;
import android.view.View;

import com.example.viewbindingexample.databinding.FragmentMainBinding;

import ir.mich.genericviewbinder.FragmentBinder;
import ir.mich.genericviewbinder.OpenFragment;

public class MainFragment extends FragmentBinder<FragmentMainBinding> implements View.OnClickListener {

    static boolean flag = false;
    int color;

    @Override
    protected void onCreateView() {
//        android:id="@+id/example_text"
        color = transfer.getExtras().getInt("color");
        binding.exampleText.setText("Color is " + hexColor(color));
        binding.btnTransfer.setOnClickListener(this);
        binding.root.setBackgroundColor(color);
    }

    private String hexColor(int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    @Override
    public void onClick(View v) {
        /*transfer.openSomeActivityForResult(
                MainActivity.class, null, result -> {
                    binding.exampleText.setText("im Back from activity");
                });*/
        if (flag = !flag) {
            Bundle bundle = new Bundle();
            bundle.putInt("color", color+1);
            transfer.openSomeFragmentForResult("key", (requestKey, result) -> {
                toast(requestKey + " | " + result.getString("code"));
            }, new OpenFragment(binding.root, new MainFragment(), null, null, bundle));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("code", "im " + hexColor(color));
            transfer.closeSomeFragmentForResult("key", bundle);
        }
    }
}