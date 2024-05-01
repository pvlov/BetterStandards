package pvlov.betterstandards.container.tuples;

public record Tuple<A, B>(A first, B second) {

    public static <A, B> Tuple<A, B> of(final A a, final B b) {
        return new Tuple<>(a, b);
    }
}
