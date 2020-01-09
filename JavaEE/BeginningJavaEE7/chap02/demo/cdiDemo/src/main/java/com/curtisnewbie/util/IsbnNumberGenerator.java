package com.curtisnewbie.util;


/**
 * ISBN's implementation of the NumberGenerator.
 */
@Digits(value = Num.THIRTEEN)
public class IsbnNumberGenerator implements NumberGenerator {

    @Override
    public String generateNumber() {
        String isbn = "13-84356-" + NumberGenerator.randomNumber();
        return isbn;
    }
}
