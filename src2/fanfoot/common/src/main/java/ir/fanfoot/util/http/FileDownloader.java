package ir.fanfoot.util.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileDownloader {

    private String uri;
    private String userAgent;
    private OutputStream out;
    private boolean closeStream;

    public FileDownloader(String uri, OutputStream out) {
        this(uri, out, true);
    }

    public FileDownloader(String uri, OutputStream out, boolean closeStream) {
        this.uri = uri;
        this.userAgent = "";
        this.out = out;
        this.closeStream = closeStream;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public boolean isCloseStream() {
        return closeStream;
    }

    public void setCloseStream(boolean closeStream) {
        this.closeStream = closeStream;
    }

    public void download() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(uri);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            InputStream inputStream = httpResponse.getEntity().getContent();
            int b;
            while ((b = inputStream.read()) != -1) {
                out.write(b);
            }
            if (closeStream) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            httpGet.abort();
        } finally {
        }
    }
}