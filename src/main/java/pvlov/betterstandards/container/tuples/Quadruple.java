package pvlov.betterstandards.container.tuples;

public record Quadruple<A, B, C, D>(A first, B second, C third, D fourth) {
    public static <A, B, C, D> Quadruple<A, B, C, D> of(final A a, final B b, final C c, final D d) {
        return new Quadruple<>(a, b, c, d);
    }
}
