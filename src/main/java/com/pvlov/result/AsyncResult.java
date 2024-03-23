package com.pvlov.result;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class AsyncResult<T> {
    private CompletableFuture<T> future;

    public T await() {
        return future.join();
    }

    public T await(Function<Throwable, T> resolver) {
        return null;

    }

}
