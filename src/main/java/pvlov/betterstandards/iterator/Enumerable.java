package pvlov.betterstandards.iterator;
@FunctionalInterface
public interface Enumerable<T> {
    Enumerator<T> enumerator();
    default Iterable<T> intoIterable() {
        return () -> enumerator().intoIterator();
    }

    default Enumerable<T> fromIterable(final Iterable<T> source) {
        return () -> Enumerator.fromIterator(source.iterator());
    }
}
