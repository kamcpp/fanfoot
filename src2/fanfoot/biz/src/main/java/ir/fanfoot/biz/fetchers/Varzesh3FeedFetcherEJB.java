package ir.fanfoot.biz.fetchers;

import com.sun.syndication.io.FeedException;
import ir.fanfoot.biz.ConfigurationEJB;
import ir.fanfoot.biz.dao.NewsAgencyDAO;
import ir.fanfoot.biz.dao.NewsDAO;
import ir.fanfoot.util.FeedFetcher;
import ir.fanfoot.util.FeedItem;
import ir.telefa.domain.News;
import ir.telefa.domain.NewsAgency;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class Varzesh3FeedFetcherEJB {

    @EJB
    private ConfigurationEJB configurationEJB;

    @EJB
    private NewsDAO newsDAO;

    @EJB
    private NewsAgencyDAO newsAgencyDAO;

    @Inject
    private FeedFetcher feedFetcher;

    private NewsAgency newsAgency;

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void fetchRSS() {
        newsAgency = newsAgencyDAO.getByEnglishQualifiedName("varzesh3");
        try {
            List<FeedItem> feedItems = feedFetcher.fetch(configurationEJB.getVarzesh3RSSURL());
            Pattern pattern = Pattern.compile("itemid=(\\d+)");
            for (FeedItem feedItem : feedItems) {
                Matcher m = pattern.matcher(feedItem.getUri());
                if (m.find()) {
                    String sourceId = m.group(1).trim();
                    if (!newsDAO.sourceIdExists(newsAgency, sourceId)) {
                        News news = new News();
                        news.setAuthor(feedItem.getAuthor().trim());
                        news.setFullDescription(null); // TODO
                        news.setHasImage(false); // TODO
                        news.setHasVideo(false); // TODO
                        news.setShown(true);
                        news.setLink(feedItem.getLink().trim());
                        news.setLongDescription(feedItem.getDescription().trim());
                        news.setNewsAgency(newsAgency);
                        news.setNumberOfVisits(0);
                        news.setPublishDate(new Date().getTime());
                        news.setQuestion(null); // TODO
                        news.setShortDescription(feedItem.getDescription().trim());
                        news.setSourceId(sourceId);
                        news.setSourceLink(feedItem.getLink().trim());
                        news.setSourcePublishDate(feedItem.getPublishDate());
                        news.setSourceURL(feedItem.getUri().trim());
                        news.setTitle(feedItem.getTitle().trim());
                        newsDAO.saveOrUpdate(news);
                    }
                }
            }
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
    }
}
