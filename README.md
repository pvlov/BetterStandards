# BetterStandards add QoL to your Java Development

### Results

A Result is a container-type that is meant to be used in place of _unchecked_ exceptions.Similar to `Optional.ofNullable()` they provide a safe wrapper 
around invoking methods that might throw `RuntimeExceptions` and empower you to write expressive and clear code. Furthermore, when used like a Tuple they eliminate
the overhead of Exceptions and provide a nice API to work with errors. They are heavily inspired by [Ocamls Result library](https://v2.ocaml.org/api/Result.html).

Here is an Example on how to use them:

```java 
class Main {
    public static void main(String[] args) {
       final String somethingToParse = "This is not an Integer";
       final int parsed = Result.of(() -> Integer.parseInt(somethingToParse)).orElse(72);
    }
    
    public Result<Integer, CustomError> fun(final String input) {
        if ("fun".equals(input)) {
            return Ok.of("You have fun with Results!");
        }
        return Err.of(CustomError.UKNOWN);
    }
    
    
    public enum CustomError {
        UKNOWN
    }
}  
```

### Tuples

You know them, you love them. They are your basic 2,3 and 4-Tuple.

### Enumerator and Enumerable

Ever wanted to use enumerate() on a collection? Now you can.


