package pvlov.betterstandards.functional;

@FunctionalInterface
public interface ExceptionallyFunction<A, R, E extends Throwable> {
    R apply(final A a) throws E;
}
