package pvlov.betterstandards.iterator;

import pvlov.betterstandards.tuples.Tuple;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Enumerator<T> {

    boolean hasNext();

    Tuple<Integer, T> next() throws NoSuchElementException;

    default Iterator<T> asIterator() {
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
