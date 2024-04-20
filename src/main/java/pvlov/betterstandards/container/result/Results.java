package pvlov.betterstandards.container.result;

import pvlov.betterstandards.functional.QuadConsumer;
import pvlov.betterstandards.functional.TriConsumer;

import java.util.function.BiConsumer;


/**
 * Utility class to more easily work with results
 */
public class Results {
    public <A, B> void ifOk(final Result<A, ?> firstResult, final Result<B, ?> secondResult, BiConsumer<? super A, ? super B> action) {
        if (firstResult.isOk() && secondResult.isOk()) {
            action.accept(firstResult.unwrap(), secondResult.unwrap());
        }
    }

    public <A, B, C> void ifOk(final Result<A, ?> firstResult, final Result<B, ?> secondResult, final Result<C, ?> thirdResult, final TriConsumer<? super A, ? super B, ? super C> action) {
        if (firstResult.isOk() && secondResult.isOk() && thirdResult.isOk()) {
            action.accept(firstResult.unwrap(), secondResult.unwrap(), thirdResult.unwrap());
        }
    }

    public <A, B, C, D> void ifOk(final Result<A, ?> firstResult, final Result<B, ?> secondResult, final Result<C, ?> thirdResult, final Result<D, ?> fourthResult, final QuadConsumer<? super A, ? super B, ? super C, ? super D> action) {
        if (firstResult.isOk() && secondResult.isOk() && thirdResult.isOk() && fourthResult.isOk()) {
            action.accept(firstResult.unwrap(), secondResult.unwrap(), thirdResult.unwrap(), fourthResult.unwrap());
        }
    }
}
