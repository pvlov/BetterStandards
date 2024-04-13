package pvlov.betterstandards.result;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.lang.Void;

/**
 * Results are another way to represent error-prone methods, rather than throwing Exceptions
 * you return an {@link Ok}-Object on success and an {@link Err}-Object on failure of a method.
 * Results are immutable, any operation that would require mutating the internal state of the
 * Result (e.g. {@link Result#map(Function)}) will instead return a new Result with the expected internal state change.
 *
 * @param <T>   The Ok-Type
 * @param <E>   The Error-Type
 */
public sealed interface Result<T, E> permits Ok, Err {
    /**
     * This method evaluates the supplying function and wraps the resulting value into the
     * respective Result-Type. If the supplying function throws any RuntimeException it is wrapped inside
     * an instance of {@link Err}, otherwise the value of the Suppliers get()-method is wrapped inside an instance of {@link Ok}
     *
     * @param supplier the supplying-function that can throw an Exception
     * @return If the supplier throws an Exception an instance of {@link Err}
     *         If the supplier returns a value without any Exceptions the value
     *         produced by the Supplier is returned wrapped into an instance of {@link Ok}
     */
    static <T> Result<T, RuntimeException> of(final Supplier<? extends T> supplier) {
        try {
            return Ok.of(supplier.get());
        } catch (final RuntimeException err) {
            return Err.of(err);
        }
    }

    /**
     * Runs the given runnable, catching any exceptions and returning a Result. If the runnable completes without
     * throwing an exception, an Ok(Void) result is returned. If the runnable throws an exception, an Err containing
     * the exception is returned.
     *
     * @param runnable the runnable to run
     * @return a Result containing either a Void value if the runnable completed successfully, or an Err containing the
     *         exception that was thrown
     */
    static Result<Void, RuntimeException> of(final Runnable runnable) {
        try {
            runnable.run();
            return Ok.empty();
        } catch (final RuntimeException err){
            return Err.of(err);
        }
    }
    /**
     * If the Result is an instance of {@link Ok}, returns the Ok-value, otherwise throws a RuntimeException.
     * If the Result is an instance of an empty {@link Ok} (as created by Ok.empty()), this will return null.
     *
     * @return the Ok-value if the Result is an instance of {@link Ok}
     *
     * @throws RuntimeException if the Result is an instance of {@link Err}
     */
    T unwrap() throws RuntimeException;

    /**
     * If the Result is an instance of {@link Ok}, returns true, otherwise false.
     *
     * @return true if the Result is an instance of {@link Ok},
     *         false if the Result is an instance of {@link Err}
     */
    boolean isOk();

    /**
     * Returns true if the Result is an instance of {@link Ok} and the given predicate returns true when applied to the Ok-Value,
     * otherwise returns false.
     *
     * @param condition the predicate to apply to the Ok-Value
     * @return true if the Result is an instance of {@link Ok} and the given predicate returns true when applied to the Ok-Value,
     *         otherwise returns false
     * @throws NullPointerException if the condition is null and the Result is an instance of {@link Ok}
     */
    boolean isOkAnd(final Predicate<? super T> condition);

    /**
     * If the Result is an instance of {@link Err}, returns true, otherwise false.
     *
     * @return false if the Result is an instance of {@link Ok},
     *         true if the Result is an instance of {@link Err}
     */
    boolean isErr();
    /**
     * Returns true if the Result is an instance of {@link Err} and the given predicate returns true when applied to the Err-Value,
     * otherwise returns false.
     *
     * @param condition the predicate to apply to the Err-Value
     * @return true if the Result is an instance of {@link Err} and the given predicate returns true when applied to the Err-Value,
     *         otherwise returns false
     * @throws NullPointerException if the condition is null and the Result is an instance of {@link Err}
     */
    boolean isErrAnd(final Predicate<? super E> condition);

    /**
     * If the Result is an instance of {@link Ok}, performs the given action with the Ok-value, otherwise does nothing.
     *
     * @param action the action to be performed, if the Result is an instance of {@link Ok}
     *
     * @throws NullPointerException if the action function is null and the Result is an instance of {@link Ok}
     *
     */
    void ifOk(final Consumer<? super T> action);

    /**
     * If the Result is an instance of {@link Err}, performs the given action with the Err-value, otherwise does nothing.
     *
     * @param action the action to be performed, if the Result is an instance of {@link Err}
     *
     * @throws NullPointerException if the action function is null and the Result is an instance of {@link Err}
     */
    void ifErr(final Consumer<? super E> action);

    /**
     *
     *
     * @param defaultValue the value to return if the Result is an instance of {@link Err}
     * @return the Ok-Value if the Result is an instance of {@link Ok}, else the provided default value
     */
    T orElse(final T defaultValue);

    /**
     * Returns the Result as is, if it is an instance of {@link Ok}, otherwise returns the Result produced by the supplying function.
     *
     * @param orSupplier the supplying function that produces an Optional to be returned
     * @return returns an Optional describing the value of this Optional, if the Result is an instance of {@link Ok},
     * otherwise an Optional produced by the supplying function.
     * @throws NullPointerException if the supplying function is null and the Result is an instance of {@link Err}
     */
    Result<? extends T, ? extends E> or(final Supplier<? extends Result<? extends T, ? extends E>> orSupplier);

    /**
     * If the Result is an instance of {@link Ok}, returns the ok-value, otherwise returns the value produced by the supplying function.
     *
     * @param supplier the supplying function that produces a value to be returned
     *
     * @return the ok-value, if the Result is an instance of {@link Ok}, otherwise the value produced by the supplying function
     *
     * @throws NullPointerException if the supplying function is null and the Result is an instance of {@link Err}
     */
    T orElseGet(final Supplier<? extends T> supplier);

    /**
     *  If the Result is an instance of {@link Ok}, returns an Optional describing (as if by ofNullable) the result of applying the given mapping function to the Ok-value,
     *  otherwise returns an empty Optional. If the mapping function returns a null result then this method returns an empty Optional.
     *
     *  @param okMapper the mapping function to apply to the Result, if it is an instance of {@link Ok}
     *
     *  @return an Optional describing the result of applying the mapping function to the Ok-value of this Result,
     *          if the Result is an instance of {@link Ok}, otherwise an empty Optional
     *
     *  @throws NullPointerException if the mapping function is null
     *
     */
    <U> Optional<U> mapOk(final Function<? super T, ? extends U> okMapper);

    /**
     *  If the Result is an instance of {@link Err}, returns an Optional describing (as if by ofNullable) the result of applying the given mapping function to the Ok-value,
     *  otherwise returns an empty Optional. If the mapping function returns a null result then this method returns an empty Optional.
     *
     *  @param errMapper the mapping function to apply to the Result, if it is an instance of {@link Err}
     *
     *  @return an Optional describing the result of applying the mapping function to the Ok-value of this Result,
     *          if the Result is an instance of {@link Err}, otherwise an empty Optional
     *
     *  @throws NullPointerException if the mapping function is null
     *
     */
    <U> Optional<U> mapErr(final Function<? super E, ? extends U> errMapper);

    /**
     * This method returns the Ok-value if the Result is an instance of {@link Ok}, otherwise
     * it will throw a RuntimeException using the given Message-String.
     * It is meant to be used the same way assert() or unwrap() is used, thus the thrown RuntimeException should not be caught.
     *
     * @param message the failure message
     * @return the Ok-value if the Result is an instance of {@link Ok}, otherwise nothing
     * @throws RuntimeException if the Result is an instance of {@link Err}
     */
    T expect(final String message) throws RuntimeException;

    /**
     * This method coerces a Result into an Optional by wrapping the Ok-value into an Optional if the Result is an instance of {@link Ok},
     * otherwise it will return an empty Optional.
     *
     * @return an Optional describing the Result
     */
    Optional<T> ok();

    /**
     * This method coerces a Result into an Optional by wrapping the Err-value into an Optional if the Result is an instance of {@link Err},
     * otherwise it will return an empty Optional.
     *
     * @return an Optional describing the Result
     */
    Optional<E> err();


    /** This method maps the Ok-Value of this Result using the provided mapping function, if it
     *  is an instance of {@link Ok}, otherwise it will return the provided default Value.
     *
     *
     * @param okMapper      the mapper function
     * @param defaultValue  the default value
     * @return Returns the result of applying the mapping function to the Ok-Value of the result
     *         if the Result is an instance of {@link Ok}, otherwise returns the provided default value
     */
    <U> U mapOr(final Function<? super T, ? extends U> okMapper, final U defaultValue);

    /** This method maps the Ok-Value of this Result using the provided mapping function, if it
     *  is an instance of {@link Ok}, otherwise if the Result is an instance of {@link Err} it
     *  will return the result of applying the provided error mapping function to the Error-Value.
     *
     *
     * @param okMapper  the ok mapper function
     * @param errMapper the error mapper function
     * @return Returns the result of applying the mapping function to the Ok-Value of the result
     *         if the Result is an instance of {@link Ok}, otherwise returns the result of applying the
     *         provided error mapping function to the Error-Value.
     * @throws NullPointerException if either the Result is an instance of {@link Ok} and the ok mapper function is null or
     *                              the Result is an instance of {@link Err} and the err mapper function is null.
     */
    <U> U mapOrElse(final Function<? super T, ? extends U> okMapper, final Function<? super E, ? extends U> errMapper);

    /**
     * Returns a new Result containing the result of mapping the Ok-Value using the provided mapper function if
     * this Result is an instance of {@link Ok}, otherwise just returns the {@link Err} as is.
     *
     * @param okMapper  the mapper function
     * @return returns a new Result with the Ok-Value of this Result mapped using the provided mapper function,
     *         if it is an instance of {@link Ok}
     * @throws NullPointerException if the mapper function is null and the Result is an instance of {@link Ok}.
     */
    <U> Result<U, E> map(final Function<? super T, ? extends U> okMapper);

    /**
     * Peeks at the Ok-Value of this Result, if it is an instance of {@link Ok},
     * and invokes the given consumer with the Ok-Value.
     *
     * @param okConsumer the consumer to invoke with the Ok-Value, if this Result is an instance of {@link Ok}
     * @return returns this Result
     * @throws NullPointerException if the consumer is null and this Result is an instance of {@link Ok}
     */
    Result<T, E> peekOk(final Consumer<? super T> okConsumer);

    /**
     * Peeks at the Err-Value of this Result, if it is an instance of {@link Err},
     * and invokes the given consumer with the Err-Value.
     *
     * @param errConsumer the consumer to invoke with the Err-Value, if this Result is an instance of {@link Err}
     * @return returns this Result
     * @throws NullPointerException if the consumer is null and this Result is an instance of {@link Err}
     */
    Result<T, E> peekErr(final Consumer<? super E> errConsumer);

    /**
     * Runs the corresponding consumer on the Ok-Value of this Result, if it is an instance of {@link Ok},
     * otherwise runs the corresponding consumer on the Err-Value of this Result, if it is an instance of
     * {@link Err}.
     *
     * @param okConsumer the consumer to invoke with the Ok-Value, if this Result is an instance of {@link Ok}
     * @param errConsumer the consumer to invoke with the Err-Value, if this Result is an instance of {@link Err}
     * @throws NullPointerException if the okConsumer is null and this Result is an instance of {@link Ok} or if the errConsumer is null and this Result is an instance of {@link Err}
     */
    void match(final Consumer<? super T> okConsumer, final Consumer<? super E> errConsumer);

    /**
     * Voids the Ok-Value of this Result if the Result is an instance of {@link Ok}
     *
     * @return returns a voided Result, where the Ok-Value is discarded if this Result is an instance of {@link Ok}.
     */
    Result<Void, E> toVoid();

    /**
     * Applies the given predicate to the Ok-Value of this Result if it is an instance of {@link Ok} and returns an {@link Ok} if
     * the given Predicate returns true for the Ok-Value. Otherwise this returns an {@link Err} containing a {@link NoSuchElementException}
     *
     * @param condition the condition to check
     * @return A Result containing the unchanged Ok-Value if it passes the given Predicate-test, otherwise an Err with a NoSuchElementException
     * @throws NullPointerException if the Result is an instance of {@link Ok} and the given Predicate is null
     */
    Result<T, NoSuchElementException> filter(final Predicate<? super T> condition);
}