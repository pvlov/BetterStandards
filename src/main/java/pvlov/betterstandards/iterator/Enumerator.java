package pvlov.betterstandards.iterator;

import pvlov.betterstandards.container.tuples.Tuple;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator-type that returns a Tuple considering of the index of the element as well as the element itself.
 * The next() method should be zero-based.
 *
 * @param <T> the type of the Elements
 */
public interface Enumerator<T> {

    static <T> Enumerator<T> fromIterator(final Iterator<T> source) {
        return new Enumerator<>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return source.hasNext();
            }

            @Override
            public Tuple<Integer, T> next() throws NoSuchElementException {
                return new Tuple<>(counter++, source.next());
            }
        };
    }

    boolean hasNext();

    Tuple<Integer, T> next() throws NoSuchElementException;

    default Iterator<T> intoIterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return Enumerator.this.hasNext();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return Enumerator.this.next().second();
            }
        };
    }
}
