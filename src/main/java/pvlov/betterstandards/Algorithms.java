package pvlov.betterstandards;

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
}
