package pvlov.betterstandards.functional;

@FunctionalInterface
public interface ExceptionallyRunnable<E extends Throwable> {
    void run() throws E;
}
