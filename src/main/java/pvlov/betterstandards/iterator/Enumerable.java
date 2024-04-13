package pvlov.betterstandards.iterator;
@FunctionalInterface
public interface Enumerable<T> {
    Enumerator<T> enumerator();
    default Iterable<T> asIterable() {
        return () -> enumerator().asIterator();
    }
}
