package pvlov.betterstandards.container.tuples;

public record Triple<A, B, C>(A first, B second, C third) {
    public static <A, B, C> Triple<A, B, C> of(final A a, final B b, final C c) {
        return new Triple<>(a, b, c);
    }
}
