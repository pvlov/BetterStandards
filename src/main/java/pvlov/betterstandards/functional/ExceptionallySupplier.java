package pvlov.betterstandards.functional;

@FunctionalInterface
public interface ExceptionallySupplier<T, E extends Throwable> {
    T get() throws E;
}
