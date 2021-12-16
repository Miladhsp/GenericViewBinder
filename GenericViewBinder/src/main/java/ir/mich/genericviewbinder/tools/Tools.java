package ir.mich.genericviewbinder.tools;

import java.util.Map;

public class Tools {
    @SafeVarargs
    public static <I> I[] arrayCreator(I... args) {
        return args;
    }

    public static <K, V> void forEach(
            Map<K, V> map,
            Functions._2<K, V> action
    ) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            action.apply(entry.getKey(), entry.getValue());
        }
    }

}
