package ir.fanfoot.biz.fetchers;

import ir.fanfoot.util.HttpDownloader;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.inject.Inject;
import java.util.concurrent.*;

@Stateless
@LocalBean
public class DownloaderEJB {

    @Inject
    private HttpDownloader httpDownloader;

    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(50);
    }

    public void downloadToFile(final String url, final String filePath) {
        downloadToFile(url, filePath, null);
    }

    public void downloadToFile(final String url, final String filePath, final Runnable postOperation) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(30000);
                    httpDownloader.downloadToFile(url, filePath);
                    if (postOperation != null) {
                        postOperation.run();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public  Future<String> downloadAsString(final String url) {
        return downloadAsString(url, null);
    }

    public Future<String> downloadAsString(final String url, final Runnable postOperation) {
        return executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String content = httpDownloader.downloadAsString(url);
                if (postOperation != null) {
                    postOperation.run();
                }
                return content;
            }
        });
    }
}
