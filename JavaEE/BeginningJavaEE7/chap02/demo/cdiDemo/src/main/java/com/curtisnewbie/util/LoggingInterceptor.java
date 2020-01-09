package com.curtisnewbie.util;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

/**
 * Interceptor that logs the methods invocation, objects construction and destruction details
 */
@Interceptor
@Loggable
public class LoggingInterceptor {

    @Inject
    private Logger logger;

    /**
     * log method invocation and exit
     */
    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        var className = ic.getTarget().getClass().getName();
        var methodName = ic.getMethod().getName();
        // log method entry
        logger.entering(className, methodName);
        try {
            return ic.proceed();
        } finally {
            // log method exit
            logger.exiting(className, methodName);
        }
    }


}
