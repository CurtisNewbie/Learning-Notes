package com.curtisnewbie;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class GzipServletOutputStream extends ServletOutputStream {

    static Logger logger = LogManager.getLogger(GzipServletOutputStream.class);

    private GZIPOutputStream gzipOut;

    public GzipServletOutputStream(OutputStream outputStream) throws IOException {
        super();
        logger.info(this.getClass() + " Initialised.");
        this.gzipOut = new GZIPOutputStream(outputStream);
    }

    @Override
    public void close() throws IOException {
        if (gzipOut != null) {
            gzipOut.close();
            logger.info(this.getClass() + " closed");
        }
    }

    @Override
    public void write(int i) throws IOException {
        logger.info(this.getClass() + " writing int");
        gzipOut.write(i);
    }

    @Override
    public void write(byte[] b) throws IOException {
        logger.info(this.getClass() + " writing byte[]");
        gzipOut.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        logger.info(this.getClass() + " writing byte[] with offset and len (length: " + b.length
                + ")");
        gzipOut.write(b, off, len);
    }

    @Override
    public boolean isReady() {
        return (gzipOut != null);
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
