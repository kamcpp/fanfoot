package ir.fanfoot.biz.fetchers;

import org.labcrypto.util.http.HttpDownloader;
import org.labcrypto.util.http.HttpDownloaderImpl;
import org.labcrypto.util.i18n.StringHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpDownloadTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        HttpDownloader httpDownloader = new HttpDownloaderImpl();

        System.out.println("Downloading page content ...");
        String content = httpDownloader.downloadAsString("http://www.varzesh3.com/news.do?itemid=1198273&title=خلاصه-بازي-پاريس‌سنت‌ژرمن\t---\tاويان-(ويديو)");
        System.out.println("Requesy made.");
        content = StringHelper.refineForRegex(content);
        System.out.println("Downloaded.");
        System.out.println(">>>>>" + content + "<<<<<<<");
        System.out.flush();

        Pattern pattern = Pattern.compile("<h1 class=\"newsTitle\">.*?\\bimg[^>]*src=\"([^\"]*)\"[^>]*>.*?\\bdiv align[^>]*>(.*?)\\B</div");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("*****************************************************");
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println("*****************************************************");
        }
    }
}
