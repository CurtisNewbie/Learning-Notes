package com.curtisnewbie.util;


/**
 * ISSN's Implementation of the NumberGenerator
 */
@Digits(value = Num.EIGHT)
public class IssnNumberGenerator implements NumberGenerator {

    @Override
    public String generateNumber() {
        String issn = "8-" + NumberGenerator.randomNumber();
        return issn;
    }
}
