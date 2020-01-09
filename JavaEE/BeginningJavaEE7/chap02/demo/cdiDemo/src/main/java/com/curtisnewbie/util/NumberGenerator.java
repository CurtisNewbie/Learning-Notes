package com.curtisnewbie.util;

import javax.inject.Inject;
import java.util.Random;

/**
 * Utility class used to generate book number
 */
public interface NumberGenerator {

    /**
     * Generate Book Number
     *
     * @return Book Number
     */
    String generateNumber();

    /**
     * Generate random positive number as a string
     * @return random positive number as a string
     */
    static String randomNumber(){
        return String.valueOf(Math.abs(new Random().nextInt()));
    }

}
