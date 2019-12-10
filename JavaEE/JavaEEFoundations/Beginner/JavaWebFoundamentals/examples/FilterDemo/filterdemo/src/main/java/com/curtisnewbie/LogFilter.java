package com.curtisnewbie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * A simple Filter class
 */
//@WebFilter(urlPatterns = "*")
public class LogFilter implements Filter {

    // use Log4j2 to log information
    static Logger logger = LogManager.getLogger(LogFilter.class);
    private FilterConfig config;
    private String filterName;

    // called once when this filter is loaded
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // we can get various information/config using this method
        if (filterConfig != null) {
            // store it as instance variable
            config = filterConfig;

            // use FilterConfig to get various useful information
            ServletContext context = filterConfig.getServletContext();
            filterName = filterConfig.getFilterName();
            String iniParamOfDB = filterConfig.getInitParameter("DBName");
            logger.info("Filter initialised: FilterName:" + filterName + ", DBName:" + iniParamOfDB);
        }
    }

    // we use a Wrapper class to wrap this request so that we can monitor calls on the request object
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // use log4j2 to log this information. Info is a specific level of information (there are 7 levels)
        logger.info("request made to '" + ((HttpServletRequest) request).getRequestURI() + "'");

        // wrap the request with a custom wrapper
        LogHttpRequestWrapper wrapper = new LogHttpRequestWrapper((HttpServletRequest) request, logger);
        // manually calling this for demo, see "LogHttpRequestWrapper"
        wrapper.getHeader("host");

        // chain the filter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        logger.info("Destroyed: FilterName:" + filterName);
    }
}


