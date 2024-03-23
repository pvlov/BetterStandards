package com.pvlov;

import com.pvlov.result.Ok;
import com.pvlov.result.Result;

public class Main {
    public static void main(String[] args) {

        final String someString = "102b";
        final var parsedInt = parseIntegerSafely(someString);

        System.out.println(parsedInt.isErr()); // prints true
        parsedInt.expect("This should've never failed?! :("); // causes a RuntimeException

    }

    private static Result<Integer, RuntimeException> parseIntegerSafely(final String input) {
        return Result.of(() -> Integer.parseInt(input));
    }
}