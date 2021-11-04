package ir.mich.genericviewbinder.tools.models;

import java.lang.reflect.Method;

public class Caller<Instance> {

    private final Instance instance;
    private final Method declaredMethod;

    public Caller(Instance instance, Method declaredMethod) {
        this.instance = instance;
        this.declaredMethod = declaredMethod;
    }

    public <Value> Value invoke(Object... args) {
        try {
            return (Value) declaredMethod.invoke(instance, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
