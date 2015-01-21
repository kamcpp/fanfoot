package ir.fanfoot.biz.fetchers;

import ir.fanfoot.biz.ConfigurationEJB;
import ir.fanfoot.biz.dao.NewsAgencyDAO;
import ir.fanfoot.biz.dao.NewsDAO;
import ir.fanfoot.util.*;
import ir.fanfoot.domain.News;
import ir.fanfoot.domain.NewsAgency;
import org.apache.commons.io.FilenameUtils;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class Varzesh3FetcherEJB {

    @EJB
    private ConfigurationEJB configurationEJB;

    @EJB
    private NewsDAO newsDAO;

    @EJB
    private NewsAgencyDAO newsAgencyDAO;

    @EJB
    private TagAssignerEJB tagAssignerEJB;

    @EJB
    private DownloaderEJB downloaderEJB;

    @Inject
    private FeedFetcher feedFetcher;

    @Inject
    private HttpDownloader httpDownloader;

    @Inject
    private ImageResizer imageResizer;

    private NewsAgency newsAgency;

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void fetch() {
        try {
            System.out.println("Fetching varzesh3 ...");
            newsAgency = newsAgencyDAO.getByEnglishQualifiedName("varzesh3");
            List<FeedItem> feedItems = feedFetcher.fetch(configurationEJB.getVarzesh3RSSURL());
            Pattern pattern = Pattern.compile("itemid=(\\d+)");
            for (FeedItem feedItem : feedItems) {
                Matcher m = pattern.matcher(feedItem.getUri());
                if (m.find()) {
                    String sourceId = m.group(1).trim();
                    if (!newsDAO.sourceIdExists(newsAgency, sourceId)) {
                        News news = new News();
                        news.setSourceId(sourceId);
                        news.setNewsAgency(newsAgency);
                        news.setAuthor(feedItem.getAuthor().trim());
                        news.setSourceLink(feedItem.getLink().trim());
                        news.setSourcePublishDate(feedItem.getPublishDate());
                        news.setPublishDate(new Date().getTime());
                        news.setShown(true);
                        news.setTitle(feedItem.getTitle().trim());
                        news.setShortDescription(feedItem.getDescription().trim());
                        news.setNumberOfVisits(0);
                        news.setHasImage(false);
                        news.setHasVideo(false);
                        newsDAO.saveOrUpdate(news);
                        getNewsStuff(news);
                        tagAssignerEJB.assignTags(news);
                    }
                }
            }
            System.out.println("Fetched.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getNewsStuff(News news)
            throws IOException, URISyntaxException, ExecutionException, InterruptedException {
        Future<String> future = downloaderEJB.downloadAsString(news.getSourceLink());
        String content = future.get();
        content = StringHelper.refineForRegex(content);
        Pattern pattern = Pattern
                .compile("<h1 class=\"newsTitle\">.*?\\bimg[^>]*src=\"([^\"]*)\"[^>]*>.*?\\bdiv align[^>]*>(.*?)\\B</div");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            downloadImage(news, matcher.group(1));
            news.setFullDescription("<div style=\"text-align: right;\">" + matcher.group(2) + "</div>");
        } else {
            pattern = Pattern.compile("<h1 class=\"newsTitle\">.*?\\bimg[^>]*src=\"([^\"]*)\"[^>]*>");
            matcher = pattern.matcher(content);
            if (matcher.find()) {
                downloadImage(news, matcher.group(1));
            }
        }
        pattern = Pattern.compile("<div id=\"player\"[^>]*href=\"([^\"]*)\"");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            downloadVideo(news, matcher.group(1));
        }
        pattern = Pattern.compile("<ul class=\"itemTags\">(.*?)\\B</ul");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            String tagItemsString = matcher.group(1);
            pattern = Pattern.compile("<li>[^<]*<a[^>]*>([^<]*)</a>");
            matcher = pattern.matcher(tagItemsString.trim());
            List<String> tags = new ArrayList<>();
            while (matcher.find()) {
                String tagString = matcher.group(1);
                tagString = tagString
                        .replace("(", "")
                        .replace(")", "")
                        .replace("[", "")
                        .replace("]", "").trim();
                try {
                    Integer.parseInt(tagString);
                } catch (Exception e) {
                    if (tagString.length() > 2) {
                        tags.add(tagString);
                    }
                }
            }
            newsDAO.addTags(news, tags);
        }
        news.setQuestion(null); // TODO
    }

    private void downloadImage(News news, String imageLink) throws IOException, URISyntaxException {
        news.setHasImage(true);
        news.setImageLink(imageLink);
        news.setImageFileExtension(FilenameUtils.getExtension(imageLink));
        final String fullOriginalImageFilePath = configurationEJB.getBaseDownloadPath() + "images/" + news.getId() + "." + news.getImageFileExtension();
        final String resizedImageFixedToWidth64FilePath = configurationEJB.getBaseDownloadPath() + "images/" + news.getImageFileNameByWidth(64);
        downloaderEJB.downloadToFile(imageLink, fullOriginalImageFilePath, new Runnable() {
            @Override
            public void run() {
                try {
                    imageResizer.resizeWithWidthFixed(fullOriginalImageFilePath, resizedImageFixedToWidth64FilePath, 64);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void downloadVideo(News news, String videoLink) throws IOException, URISyntaxException {
        news.setHasVideo(true);
        news.setVideoLink(videoLink);
        news.setVideoFileExtension(FilenameUtils.getExtension(videoLink));
        String videoFilePath = configurationEJB.getBaseDownloadPath() + "videos/" + news.getId() + "." + news.getVideoFileExtension();
        downloaderEJB.downloadToFile(videoLink, videoFilePath);
    }
}
