package com.curtisnewbie;

import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * This is a wrapper class the wrap a Http request. We use this to wrap a request in the Filter object so that we can
 * still have access to it after sending it further down the chain, we can log information of this request by adding a
 * logging operation in this class, and pass this object further down the chain, so every time a method is called, the
 * logger records this information.
 */
public class LogHttpRequestWrapper extends HttpServletRequestWrapper {

    // we pass the logger to this wrapper when we wrap a request in the Filter
    private Logger logger;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request the {@link HttpServletRequest} to be wrapped.
     * @throws IllegalArgumentException if the request is null
     */
    public LogHttpRequestWrapper(HttpServletRequest request, Logger logger) {
        super(request);
        this.logger = logger;
    }

    // We overide the methods and add the logging operation in it, so that every time the method is called, information
    // is logged.
    @Override
    public String getHeader(String name) {
        logger.info("getHeader() being called");
        return super.getHeader(name);
    }
}
