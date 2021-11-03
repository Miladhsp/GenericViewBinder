package ir.mich.genericviewbinder;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Access<Instance> {
    private final Class<?> clazz;
    private final Class<?>[] parameterTypes;
    private final Instance instance;
    private final String fieldName;
    private final String methodName;

    private Access(String fieldName, String methodName, Class<?> clazz, Instance instance, Class<?>... parameterTypes) {
        this.fieldName = fieldName;
        this.methodName = methodName;
        this.clazz = clazz;
        this.instance = instance;
        this.parameterTypes = parameterTypes;
    }

    public static <Instance> Access<Instance> Field(String fieldName, Class<?> clazz, Instance instance) {
        return new Access<>(fieldName, null, clazz, instance, null);
    }

    public static <Instance> Access<Instance> Method(String methodName, Class<?> clazz, Instance instance, Class<?>... parameterTypes) {
        return new Access<>(null, methodName, clazz, instance, parameterTypes);
    }


    public Injector<Instance> setModifier_Field(Boolean PRIVATE, Boolean FINAL) {
        Field declaredField = null;
        try {
            declaredField = clazz.getDeclaredField(fieldName);
            if (PRIVATE != null) {
                declaredField.setAccessible(!PRIVATE);
            }
            if (FINAL != null) {
                Field modifiersField = Field.class.getDeclaredField("slot");
                modifiersField.setAccessible(true);
                modifiersField.setInt(declaredField, declaredField.getModifiers()
                        & ((FINAL) ? Modifier.FINAL : ~Modifier.FINAL));
                //modifiersField.set(modifiersField, 3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Injector<>(instance, declaredField);
    }

    public Caller<Instance> setModifier_Method(Boolean PRIVATE) {
        Method declaredMethod = null;
        try {
            declaredMethod = clazz.getDeclaredMethod(methodName, parameterTypes);
            declaredMethod.setAccessible(!PRIVATE);
        } catch (SecurityException | IllegalArgumentException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new Caller<>(instance, declaredMethod);
    }

    public static class Injector<Instance> {

        private Instance instance;
        private Field declaredField;

        private Injector(Instance instance, Field declaredField) {
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

    public class Caller<Instance> {

        private Instance instance;
        private Method declaredMethod;

        private Caller(Instance instance, Method declaredMethod) {
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
}

