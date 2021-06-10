package com.hlgx.minio.common;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author shixingjie
 * @Date 2021/6/10 10:00
 */
public class ProgressInputStream extends FilterInputStream {


    private UploadListener monitor;
    private long             nread = 0;
    private long             size = 0;

    public ProgressInputStream(InputStream in, UploadListener listener) {
        super(in);
        try {
            size = in.available();
        }
        catch(IOException ioe) {
            size = 0;
        }
        monitor = listener;
    }


    public UploadListener getListener() {
        return monitor;
    }


    /**
     * Overrides <code>FilterInputStream.read</code>
     * to update the progress monitor after the read.
     */
    public int read() throws IOException {
        int c = in.read();
        if (c >= 0) notifyProcess(++nread);
        return c;
    }


    /**
     * Overrides <code>FilterInputStream.read</code>
     * to update the progress monitor after the read.
     */
    public int read(byte b[]) throws IOException {
        int nr = in.read(b);
        if (nr > 0) notifyProcess(nread += nr);

        return nr;
    }


    /**
     * Overrides <code>FilterInputStream.read</code>
     * to update the progress monitor after the read.
     */
    public int read(byte b[],
                    int off,
                    int len) throws IOException {
        int nr = in.read(b, off, len);
        if (nr > 0) notifyProcess(nread += nr);
        return nr;
    }


    /**
     * Overrides <code>FilterInputStream.skip</code>
     * to update the progress monitor after the skip.
     */
    public long skip(long n) throws IOException {
        long nr = in.skip(n);
        if (nr > 0) notifyProcess(nread += nr);
        return nr;
    }


    /**
     * Overrides <code>FilterInputStream.close</code>
     * to close the progress monitor as well as the stream.
     */
    public void close() throws IOException {
        in.close();
    }


    /**
     * Overrides <code>FilterInputStream.reset</code>
     * to reset the progress monitor as well as the stream.
     */
    public synchronized void reset() throws IOException {
        in.reset();
        nread = size - in.available();

    }

    private void  notifyProcess(long read){
        if(monitor != null){
            monitor.onProgress((int) ((float)read * 100 / size));
        }
    }


}
