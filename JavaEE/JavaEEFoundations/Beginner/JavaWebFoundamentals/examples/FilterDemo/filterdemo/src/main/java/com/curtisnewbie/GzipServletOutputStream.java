package com.curtisnewbie;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipServletOutputStream extends ServletOutputStream {

    private boolean closed = false;
    private HttpServletResponse servletResponse;
    private GZIPOutputStream gzipOut;
    private ServletOutputStream originOut;
    private ByteArrayOutputStream byteArrayOut;

    public GzipServletOutputStream(HttpServletResponse servletResponse) throws IOException {
        super();
        this.servletResponse = servletResponse;
        this.byteArrayOut = new ByteArrayOutputStream();
        this.originOut = servletResponse.getOutputStream();
        this.gzipOut = new GZIPOutputStream(byteArrayOut);
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException("Output stream has been closed.");
        } else {
            gzipOut.finish();
            // GzipOutputStream wraps this ByteArrayOutputStream, the data are not automatically push to the client,
            // instead the data are compressed, and stored in the byteArrayOut (in its' buffer internally), so the data
            // are extracted when we close the byteArrayOut or get its internal buffer as below.
            byte[] buffer = byteArrayOut.toByteArray();

            // set content length and type so that client know the response content is compressed.
            servletResponse.addHeader("Content-Encoding", "gzip");
            servletResponse.addHeader("Content-Length", Integer.toString(buffer.length));

            // this buffer is the data stored in the byteArrayOut, and these data are the compressed data, so we
            // still need the original ServletOutputStream to push the data back to the client
            originOut.write(buffer);
            originOut.flush();
            originOut.close();
            closed = true;
        }
    }

    @Override
    public void write(int i) throws IOException {
        gzipOut.write(i);
    }

    @Override
    public void write(byte[] b) throws IOException {
        gzipOut.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        gzipOut.write(b, off, len);
    }

    @Override
    public boolean isReady() {
        return !closed;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }

}
