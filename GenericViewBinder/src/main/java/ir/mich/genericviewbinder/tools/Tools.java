package ir.mich.genericviewbinder.tools;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Tools {
    @SafeVarargs
    public static <I> I[] array(I... args) {
        return args;
    }

    @SafeVarargs
    public static <I> List<I> list(I... args) {
        return Arrays.asList(args);
    }

    public static <K, V> void forEach(
            Map<K, V> map,
            Functions.Void._2<K, V> action
    ) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.apply(entry.getKey(), entry.getValue());
        }
    }

    public static <E> void forEach(
            Collection<E> collection,
            Functions.Void._1<E> action
    ) {
        for (E e : collection) {
            action.apply(e);
        }
    }

}
