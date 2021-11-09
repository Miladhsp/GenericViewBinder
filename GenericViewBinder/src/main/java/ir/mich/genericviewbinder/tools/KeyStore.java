package ir.mich.genericviewbinder.tools;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import ir.mich.genericviewbinder.base.App;

public class KeyStore {

    public static SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(App.getContext());
    }

    public static SharedPreferences getSharedPreferences(String name) {
        return App.getAppContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences(String name, int mode) {
        return App.getAppContext().getSharedPreferences(name, mode);
    }
}