package com.curtisnewbie;

import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.PrintWriter;

/**
 * Wrapper of Http Response, it's used to compress response data using GZip. The way we wrap the response and compress
 * the response data, is by overriding some of methods to use and get original outputStream and wrapping the output
 * stream with GZIPoutputstream.
 */
public class GzipHttpResponseWrapper extends HttpServletResponseWrapper {

    private HttpServletResponse originalResponse;
    private ServletOutputStream gzipOut = null;
    private PrintWriter gzipWriter = null;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response the {@link HttpServletResponse} to be wrapped.
     * @throws IllegalArgumentException if the response is null
     */
    public GzipHttpResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        this.originalResponse = response;
    }

    /**
     * Close all output streams
     */
    public void closeResponse() throws IOException {
        if (gzipOut != null) {
            gzipOut.close();
        }
        if (gzipWriter != null) {
            gzipWriter.close();
        }
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (gzipOut == null) {
            gzipOut = createGzipOutputStream();
        }

        if (gzipWriter == null) {
            gzipWriter = new PrintWriter(gzipOut);
        }
        return gzipWriter;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (gzipOut == null) {
            gzipOut = createGzipOutputStream();
        }
        return gzipOut;
    }

    @Override
    public void flushBuffer() throws IOException {
        gzipOut.flush();
    }

    /**
     * Create Gzip ServeltOutputStream
     */
    private ServletOutputStream createGzipOutputStream() throws IOException {
        return new GzipServletOutputStream(originalResponse.getOutputStream());
    }
}
