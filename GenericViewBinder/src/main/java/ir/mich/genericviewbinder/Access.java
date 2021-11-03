package ir.mich.genericviewbinder;

import java.lang.reflect.Field;

class Access {
    protected static <Instatnse, Value> void updateField(
            Class<?> clazz, Instatnse instatnse, String fieldName, Value newValue) {
        try {
            Field declaredField = clazz.getDeclaredField(fieldName);
            boolean accessible = declaredField.isAccessible();
            declaredField.setAccessible(true);
            declaredField.set(instatnse, newValue);
            declaredField.setAccessible(accessible);
        } catch (NoSuchFieldException
                | SecurityException
                | IllegalArgumentException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
