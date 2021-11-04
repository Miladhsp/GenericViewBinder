package ir.mich.genericviewbinder.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import ir.mich.genericviewbinder.tools.models.Caller;
import ir.mich.genericviewbinder.tools.models.Injector;

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
            boolean isAccessible = declaredField.isAccessible();
            if (FINAL != null) {
                declaredField.setAccessible(true);
                Field modifiersField = Field.class.getDeclaredField("slot");
                modifiersField.setAccessible(true);
                modifiersField.set(modifiersField, (FINAL) ? -1 : 3);
                if (PRIVATE == null) {
                    declaredField.setAccessible(isAccessible);
                }
            }
            if (PRIVATE != null) {
                declaredField.setAccessible(!PRIVATE);
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

}



