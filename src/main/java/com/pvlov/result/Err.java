
package com.pvlov.result;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Err<Void, E> implements Result<Void, E> {

    final E errorValue;

    private Err(final E e) {
        this.errorValue = e;
    }

    public static <Void, E> Err<Void, E> of(final E errorValue) {
        return new Err<>(errorValue);
    }
    @Override
    public Void unwrap() throws RuntimeException {
        throw new RuntimeException("Calling unwrap() on Err!");
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isErr() {
        return true;
    }

    @Override
    public void ifOk(final Consumer<? super Void> action) {
    }

    @Override
    public void ifErr(final Consumer<? super E> action) {
        action.accept(errorValue);
    }

    @Override
    public Optional<? extends Void> orElse(final Supplier<? extends Optional<? extends Void>> orSupplier) {
        return orSupplier.get();
    }

    @Override
    public Void orElseGet(final Supplier<? extends Void> supplier) {
        return supplier.get();
    }

    @Override
    public <U> Optional<U> mapOk(final Function<? super Void, ? extends U> okMapper) {
        return Optional.empty();
    }

    @Override
    public <U> Optional<U> mapErr(Function<? super E, ? extends U> errMapper) {
        return Optional.of(errMapper.apply(errorValue));
    }

    @Override
    public Void expect(final String message) throws RuntimeException {
        throw new RuntimeException(message);
    }

    @Override
    public Optional<Void> ok() {
        return Optional.empty();
    }

    @Override
    public Optional<E> err() {
        return Optional.of(errorValue);
    }

    @Override
    public <U> U mapOr(final Function<? super Void, ? extends U> okMapper, final U defaultValue) {
        return defaultValue;
    }

    @Override
    public <U> U mapOrElse(final Function<? super Void, ? extends U> okMapper, final Function<? super E, ? extends U> errMapper) {
        return errMapper.apply(errorValue);
    }
    @Override
    public <U> Result<U, E> map(Function<? super Void, ? extends U> okMapper) {
        return Err.of(errorValue);
    }

    @Override
    public Result<Void, E> peekOk(Consumer<? super Void> okConsumer) {
        return this;
    }

    @Override
    public Result<Void, E> peekErr(Consumer<? super E> errConsumer) {
        errConsumer.accept(errorValue);
        return this;
    }

    @Override
    public void match(Consumer<? super Void> okConsumer, Consumer<? super E> errConsumer) {
        errConsumer.accept(errorValue);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Err<?, ?> otherErr && Objects.equals(otherErr.errorValue, errorValue);
    }

    @Override
    public String toString() {
        return "Err(" + errorValue  + ")";
    }
}