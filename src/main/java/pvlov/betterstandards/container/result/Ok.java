package pvlov.betterstandards.container.result;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public record Ok<T, Void>(T okValue) implements Result<T, Void> {
    public static <T, Void> Ok<T, Void> of(final T okValue) {
        return new Ok<>(Objects.requireNonNull(okValue));
    }

    public static <Void> Ok<java.lang.Void, Void> empty() {
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
    public boolean isOkAnd(final Predicate<? super T> condition) {
        return condition.test(okValue);
    }

    @Override
    public boolean isErr() {
        return false;
    }

    @Override
    public boolean isErrAnd(final Predicate<? super Void> condition) {
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
    public T orElse(final T defaultValue) {
        return okValue;
    }

    @Override
    public Result<? extends T, Void> or(final Supplier<? extends Result<? extends T, ? extends Void>> orSupplier) {
        return this;
    }

    @Override
    public T orElseGet(final Supplier<? extends T> supplier) {
        return okValue;
    }

    @Override
    public T orElseApply(final Function<? super Void, ? extends T> function) {
        return okValue;
    }

    @Override
    public <U> Optional<U> mapOk(final Function<? super T, ? extends U> okMapper) {
        return Optional.ofNullable(okMapper.apply(okValue));
    }

    @Override
    public <U> Optional<U> mapErr(final Function<? super Void, ? extends U> errMapper) {
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
    public <U> Result<U, Void> map(final Function<? super T, ? extends U> okMapper) {
        return Ok.of(okMapper.apply(okValue));
    }

    @Override
    public <U> Result<U, Void> flatMap(final Function<? super T, ? extends Result<U, Void>> okMapper) {
        return okMapper.apply(okValue);
    }

    @Override
    public Result<T, Void> peekOk(final Consumer<? super T> okConsumer) {
        okConsumer.accept(okValue);
        return this;
    }

    @Override
    public Result<T, Void> peekErr(final Consumer<?  super Void> errConsumer) {
        return this;
    }

    @Override
    public void match(final Consumer<? super T> okConsumer, final Consumer<? super Void> errConsumer) {
        okConsumer.accept(okValue);
    }

    @Override
    public Result<java.lang.Void, Void> toVoid() {
        return Ok.empty();
    }

    @Override
    public Result<T, NoSuchElementException> filter(final Predicate<? super T> condition) {
        return condition.test(okValue) ? Ok.of(okValue) : Err.of(new NoSuchElementException("Ok-Value of the Result did not pass the given condition"));
    }

    @Override
    public Stream<T> stream() {
        return Stream.of(okValue);
    }

    @Override
    public <R> Stream<R> stream(final Function<? super T, ? extends Stream<? extends R>> flatMapper) {
        return Stream.of(okValue).flatMap(flatMapper);
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Ok<?, ?> otherOk && Objects.equals(otherOk.okValue, okValue);
    }

    @Override
    public String toString() {
        return "Ok(" + okValue + ")";
    }
}