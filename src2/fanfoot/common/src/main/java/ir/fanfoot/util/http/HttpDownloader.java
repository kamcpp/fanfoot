package ir.fanfoot.util.http;

import java.io.IOException;
import java.net.URISyntaxException;

public interface HttpDownloader {

    String downloadAsString(String url) throws IOException, URISyntaxException;

    void downloadToFile(String url, String filePath) throws IOException, URISyntaxException;
}
