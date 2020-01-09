package com.curtisnewbie.util;

import javax.enterprise.inject.Alternative;

/**
 * Implmentation of Number Generator for mock test.
 */
@Alternative
@Digits(value = Num.THIRTEEN)
public class MockNumberGenerator implements NumberGenerator {

    @Override
    public String generateNumber() {
        String mock = "MOCK-" + NumberGenerator.randomNumber();
        return mock;
    }
}
