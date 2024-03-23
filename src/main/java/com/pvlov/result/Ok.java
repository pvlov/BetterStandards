package com.pvlov.result;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class Ok<T, Void> implements Result<T, Void> {
    final T okValue;

    private Ok(final T val) {
        this.okValue = val;
    }
    public static <T, Void> Ok<T, Void> of(final T okValue) {
        return new Ok<>(okValue);
    }
    public static <Void> Ok<Void, Void> empty() {
        return new Ok<>(null);
    }
    @Override
    public T unwrap() {
        return okValue;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public void ifOk(final Consumer<? super T> action) {
        action.accept(okValue);
    }

    @Override
    public void ifErr(final Consumer<? super Void> action) {
    }
    @Override
    public Optional<? extends T> orElse(final Supplier<? extends Optional<? extends T>> orSupplier) {
        return Optional.of(okValue);
    }

    @Override
    public T orElseGet(final Supplier<? extends T> supplier) {
        return okValue;
    }

    @Override
    public <U> Optional<U> mapOk(final Function<? super T, ? extends U> okMapper) {
        return Optional.ofNullable(okMapper.apply(okValue));
    }

    @Override
    public <U> Optional<U> mapErr(Function<? super Void, ? extends U> errMapper) {
        return Optional.empty();
    }

    @Override
    public T expect(final String message) throws RuntimeException {
        return okValue;
    }

    @Override
    public Optional<T> ok() {
        return Optional.of(okValue);
    }

    @Override
    public Optional<Void> err() {
        return Optional.empty();
    }

    @Override
    public <U> U mapOr(final Function<? super T, ? extends U> okMapper, final U defaultValue) {
        return okMapper.apply(okValue);
    }

    @Override
    public <U> U mapOrElse(final Function<? super T, ? extends U> okMapper, final Function<? super Void, ? extends U> errMapper) {
        return okMapper.apply(okValue);
    }

    @Override
    public <U> Result<U, Void> map(Function<? super T, ? extends U> okMapper) {
        return Ok.of(okMapper.apply(okValue));
    }

    @Override
    public Result<T, Void> peekOk(Consumer<? super T> okConsumer) {
        okConsumer.accept(okValue);
        return this;
    }

    @Override
    public Result<T, Void> peekErr(Consumer<? super Void> errConsumer) {
        return this;
    }

    @Override
    public void match(Consumer<? super T> okConsumer, Consumer<? super Void> errConsumer) {
        okConsumer.accept(okValue);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Ok<?, ?> otherOk && Objects.equals(otherOk.okValue, okValue);
    }

    @Override
    public String toString() {
        return "Ok(" + okValue  + ")";
    }
}