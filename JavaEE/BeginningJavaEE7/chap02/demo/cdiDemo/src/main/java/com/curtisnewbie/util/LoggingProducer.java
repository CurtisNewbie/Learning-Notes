package com.curtisnewbie.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggingProducer {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) throws IOException {
        var logger = Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
        return logger;
    }
}
