package ir.fanfoot.util.http;

import ir.fanfoot.util.i18n.StringHelper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;

public class HttpDownloaderImpl implements HttpDownloader {

    @Override
    public String downloadAsString(String url) throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().setUserAgent("").build();
        HttpGet httpGet = new HttpGet(StringHelper.encodeURL(url).toURI());
        HttpResponse httpResponse = httpClient.execute(httpGet);
        return IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
    }

    @Override
    public void downloadToFile(String url, String filePath) throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().setUserAgent("").build();
        HttpGet httpGet = new HttpGet(StringHelper.encodeURL(url).toURI());
        HttpResponse httpResponse = httpClient.execute(httpGet);
        IOUtils.copy(httpResponse.getEntity().getContent(), new FileOutputStream(filePath));
    }
}
