package pvlov.betterstandards.functional;

@FunctionalInterface
public interface QuadFunction<A, B, C, D, R> {
    R apply(final A a, final B b, final C c, final D d);
}
