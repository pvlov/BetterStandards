package pvlov.betterstandards;

import java.util.Comparator;
import java.util.Optional;

public class Algorithms {

    @SafeVarargs
    public static <T extends Comparable<T>> Optional<T> min(final T... objects) {
        if (objects.length == 0) {
            return Optional.empty();
        }

        T min = objects[0];
        for (final T obj : objects) {
            if (obj.compareTo(min) < 0) {
                min = obj;
            }
        }
        return Optional.of(min);
    }

    @SafeVarargs
    public static <T extends Comparable<T>> Optional<T> max(final T... objects) {
        if (objects.length == 0) {
            return Optional.empty();
        }

        T max = objects[0];
        for (final T obj : objects) {
            if (obj.compareTo(max) > 0) {
                max = obj;
            }
        }
        return Optional.of(max);
    }

    public static <T> Optional<T> min(final Comparator<T> comparator, final T... objects) {
       if (objects.length == 0) {
           return Optional.empty();
       }

       T min = objects[0];

        for (int i = 1; i < objects.length; ++i) {
            if (comparator.compare(objects[i], min) < 0) {
                min = objects[i];
            }
        }
       return Optional.of(min);
    }
}
