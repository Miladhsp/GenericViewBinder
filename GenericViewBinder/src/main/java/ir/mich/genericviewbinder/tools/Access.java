package ir.mich.genericviewbinder.tools;

import ir.mich.genericviewbinder.tools.models.Caller;
import ir.mich.genericviewbinder.tools.models.Injector;

public class Access {

    public static class Method<Instance> {
        private final boolean DECLARED;
        private final String methodName;
        private final Class<?> clazz;
        private final Instance instance;
        private final Class<?>[] parameterTypes;

        private Method(boolean DECLARED, String methodName, Class<?> clazz, Instance instance, Class<?>[] parameterTypes) {
            this.DECLARED = DECLARED;
            this.methodName = methodName;
            this.clazz = clazz;
            this.instance = instance;
            this.parameterTypes = parameterTypes;
        }

        public static <Instance> Method<Instance> builder(boolean DECLARED, String methodName, Class<?> clazz, Instance instance, Class<?>[] parameterTypes) {
            return new Method<>(DECLARED, methodName, clazz, instance, parameterTypes);
        }

        public Caller<Instance> setModifier(Boolean PRIVATE) {
            java.lang.reflect.Method method = null;
            try {
                method = DECLARED ? clazz.getDeclaredMethod(methodName, parameterTypes)
                        : clazz.getMethod(methodName, parameterTypes);
                method.setAccessible(!PRIVATE);
            } catch (SecurityException | IllegalArgumentException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            return new Caller<>(instance, method);
        }
    }

    public static class Field<Instance> {

        private final boolean DECLARED;
        private final String fieldName;
        private final Class<?> clazz;
        private final Instance instance;

        private Field(boolean DECLARED, String fieldName, Class<?> clazz, Instance instance) {
            this.DECLARED = DECLARED;
            this.fieldName = fieldName;
            this.clazz = clazz;
            this.instance = instance;
        }

        public static <Instance> Field<Instance> builder(boolean DECLARED, String fieldName, Class<?> clazz, Instance instance) {
            return new Field<>(DECLARED, fieldName, clazz, instance);
        }

        public Injector<Instance> setModifier(Boolean PRIVATE, Boolean FINAL) {
            java.lang.reflect.Field field = null;
            try {
                field = DECLARED ? clazz.getDeclaredField(fieldName)
                        : clazz.getField(fieldName);
                boolean isAccessible = field.isAccessible();
                if (FINAL != null) {
                    field.setAccessible(true);
                    java.lang.reflect.Field modifiersField = java.lang.reflect.Field.class.getDeclaredField("slot");
                    modifiersField.setAccessible(true);
                    modifiersField.set(modifiersField, (FINAL) ? -1 : 3);
                    if (PRIVATE == null) {
                        field.setAccessible(isAccessible);
                    }
                }
                if (PRIVATE != null) {
                    field.setAccessible(!PRIVATE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Injector<>(instance, field);
        }
    }


}



