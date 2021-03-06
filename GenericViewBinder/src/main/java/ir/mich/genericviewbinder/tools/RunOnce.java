package ir.mich.genericviewbinder.tools;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import ir.mich.genericviewbinder.base.App;

public class RunOnce {

    private static final String KEY = "kQkn2cLUopXw6uKKuUE8";

    public interface FirstTimeListener {
        void onFirstTime();

        void onNotFirstTime();
    }

    public static class FirstRun {
        private static HashMap<Class<?>, ArrayList<String>> map = new HashMap<>();

        public static <This> void init(@NonNull This aThis, @NonNull String key, @NonNull FirstTimeListener reviewer) {
            Class<?> clazz = aThis.getClass();
            if (map.containsKey(clazz)) {
                if (Objects.requireNonNull(map.get(clazz)).contains(key)) {
                    reviewer.onNotFirstTime();
                } else {
                    Objects.requireNonNull(map.get(clazz)).add(key);
                    reviewer.onFirstTime();
                }
            } else {
                reviewer.onFirstTime();
                ArrayList<String> list = new ArrayList<>();
                list.add(key);
                map.put(clazz, list);
            }
        }

        public static <This> boolean remove(@NonNull This aThis, @NonNull String key) {
            Class<?> clazz = aThis.getClass();
            if (map.containsKey(clazz)) {
                if (Objects.requireNonNull(map.get(clazz)).contains(key)) {
                    return Objects.requireNonNull(map.get(clazz)).remove(key);
                }
            }
            return false;
        }

        public static <This> boolean remove(@NonNull This aThis) {
            Class<?> clazz = aThis.getClass();
            if (map.containsKey(clazz)) {
                map.remove(clazz);
                return true;
            }
            return false;
        }

        public static void clear() {
            map.clear();
        }

    }

    public static class FirstInstall {
        public static void init(String key_SharedPreferences, FirstTimeListener reviewer) {
            if (!App.getSharedPreferences(KEY).getBoolean(key_SharedPreferences, false)) {
                reviewer.onFirstTime();
                SharedPreferences.Editor editor = App.getSharedPreferences(KEY).edit();
                editor.putBoolean(key_SharedPreferences, true);
                editor.apply();
            } else {
                reviewer.onNotFirstTime();
            }
        }

        public void remove(String key) {
            App.getSharedPreferences(KEY).edit().remove(KEY).apply();
        }

        public void clear() {
            App.getSharedPreferences(KEY).edit().clear().apply();
        }
    }

}
