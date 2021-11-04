package ir.mich.genericviewbinder.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Objects;

public class GenericBinder<GB> {

    public static final String INFLATE = "inflate";
    private final Class<?> clazz;

    public GenericBinder(Object This, int index) {
        clazz = ((Class<?>) ((ParameterizedType) Objects.requireNonNull(This.getClass().getGenericSuperclass())).getActualTypeArguments()[index]);
    }

    public GB inflate(LayoutInflater inflater) {
        return inflate((Object) inflater);
    }

    public GB inflate(LayoutInflater inflater, ViewGroup group) {
        return inflate(inflater, group, false);
    }

    private GB inflate(Object... args) {
        Class<?>[] parameterTypes = (args.length == 3) ?
                new Class[]{LayoutInflater.class, ViewGroup.class, boolean.class}
                : new Class[]{LayoutInflater.class};
        try {
            @SuppressWarnings("unchecked")
            GB gb = (GB) clazz.getMethod(INFLATE, parameterTypes).invoke(null, args);
            return gb;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
