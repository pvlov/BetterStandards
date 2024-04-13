package pvlov.betterstandards.iterator;

import pvlov.betterstandards.container.tuples.Tuple;

import java.util.Iterator;

@FunctionalInterface
public interface Enumerable<T> extends Iterable<Tuple<Integer, T>> {
    Enumerator<T> enumerator();
    default  Iterable<T> intoIterable() {
        return () -> enumerator().intoIterator();
    }
    @Override
    default Iterator<Tuple<Integer, T>> iterator() {
        return new Iterator<>() {
            final Enumerator<T> enumerator = Enumerable.this.enumerator();
            @Override
            public boolean hasNext() {
                return enumerator.hasNext();
            }

            @Override
            public Tuple<Integer, T> next() {
                return enumerator.next();
            }
        };
    }
    static <T> Enumerable<T> fromIterable(final Iterable<T> source) {
       return () -> Enumerator.fromIterator(source.iterator());
    }
}
