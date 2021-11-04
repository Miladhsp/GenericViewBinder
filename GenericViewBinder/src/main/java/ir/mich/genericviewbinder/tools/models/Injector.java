package ir.mich.genericviewbinder.tools.models;

import java.lang.reflect.Field;

import ir.mich.genericviewbinder.base.App;

public class Injector<Instance> {

    private final Instance instance;
    private final Field declaredField;

    public Injector(Instance instance, Field declaredField) {
        this.instance = instance;
        this.declaredField = declaredField;
    }

    public <Value> void inject(Value newValue) {
        try {
            declaredField.set(instance, newValue);
        } catch (IllegalAccessException e) {
            App.toast(e.toString());
            e.printStackTrace();
        }
    }
}