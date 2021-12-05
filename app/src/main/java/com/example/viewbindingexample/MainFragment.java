package com.example.viewbindingexample;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;

import com.example.viewbindingexample.databinding.FragmentMainBinding;

import ir.mich.genericviewbinder.base.FragmentBinder;
import ir.mich.genericviewbinder.tools.Colors;
import ir.mich.genericviewbinder.tools.RunOnce;

public class MainFragment extends FragmentBinder<FragmentMainBinding> implements View.OnClickListener {

    static boolean flag = true;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreateView() {
        int color = transfer.getExtras().getInt("color");
//        android:id="@+id/example_text"
//        view.findViewById(R.id.example_text);
        binding.exampleText.setText("Color is " + Colors.hexColor(color));
        binding.btnTransfer.setOnClickListener(this);
        binding.root.setBackgroundColor(color);
        RunOnce.FirstRun.init(this, "123", new RunOnce.FirstTimeListener() {
            @Override
            public void onFirstTime() {
                setFragmentDeepChangedListener(deep -> toast("deep : " + deep));
            }

            @Override
            public void onNotFirstTime() {
                Log.println(Log.ERROR, "color", binding.exampleText.getText().toString());
            }
        });
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