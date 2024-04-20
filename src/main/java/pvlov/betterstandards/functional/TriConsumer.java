package pvlov.betterstandards.functional;
@FunctionalInterface
public interface TriConsumer<A, B, C> {
    void accept(final A a, final B b, final C c);
}
