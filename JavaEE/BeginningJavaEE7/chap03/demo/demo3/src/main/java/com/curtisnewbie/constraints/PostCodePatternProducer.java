package com.curtisnewbie.constraints;

import com.curtisnewbie.qualifiers.PostCodePattern;

import javax.enterprise.inject.Produces;
import java.util.regex.Pattern;

public class PostCodePatternProducer {

    @Produces @PostCodePattern
    public Pattern createPostCodePattern(){
        Pattern postCodePattern = Pattern.compile("\\d{5}(-\\d{5})");
        return postCodePattern;
    }
}
