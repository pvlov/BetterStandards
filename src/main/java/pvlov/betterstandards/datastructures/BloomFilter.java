package pvlov.betterstandards.datastructures;

import java.util.BitSet;

public class BloomFilter<T> {
    // TODO: Implement

    // Expect the amount of entries to be
    // https://pages.cs.wisc.edu/~cao/papers/summary-cache/node8.html
    // https://en.wikipedia.org/wiki/Bloom_filter

    private final BitSet bitSet;
    public BloomFilter() {
        bitSet = new BitSet();
    }

    public boolean mightContain(final T data) {
        return false;
    }
}
