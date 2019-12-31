package com.curtisnewbie.util;

import java.util.Random;

public class IsbnNumberGenerator implements NumberGenerator {

    @Override
    public String generateNumber() {
        return "123-789-" + Math.abs(new Random().nextInt());
    }
}
