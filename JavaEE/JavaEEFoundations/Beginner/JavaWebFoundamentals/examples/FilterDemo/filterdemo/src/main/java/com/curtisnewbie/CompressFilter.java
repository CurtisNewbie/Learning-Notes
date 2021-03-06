package com.curtisnewbie;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


//@WebFilter(urlPatterns = "*.gzip")
public class CompressFilter implements Filter {

    // create logger to monitor this CompressFilter
    static Logger logger = LogManager.getLogger(CompressFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            String acceptGzip = ((HttpServletRequest) request).getHeader("accept-encoding");

            if (acceptGzip != null && acceptGzip.indexOf("gzip") != -1) {

                logger.info(
                        "GZIP Compression Enabled on RequestID:" + ((HttpServletRequest) request).getRequestURI() +
                                new Date().toString());
                // compress output stream by using a response wrapper
                GzipHttpResponseWrapper compressResponseWrapper =
                        new GzipHttpResponseWrapper((HttpServletResponse) response);

                // pass wrapper down the chain
                chain.doFilter(request, compressResponseWrapper);

                // set content type so that client know the response content is compressed.
                compressResponseWrapper.addHeader("Content-Encoding", "gzip");

                // after the request is processed (or the chain is finished), close stream and writer
                compressResponseWrapper.closeResponse();
                return;
            }
        }
        logger.info(
                "GZIP Compression cannot be enabled on RequestID:" + ((HttpServletRequest) request).getRequestURI() +
                        new Date().toString());
        // if not http servlet response or not accepting compression, pass original request and response objects
        chain.doFilter(request, response);
    }
}
