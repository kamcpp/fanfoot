package ir.fanfoot.biz.news;

import ir.fanfoot.biz.ConfigurationEJB;
import ir.fanfoot.util.FeedFetcher;
import ir.fanfoot.util.FeedItem;
import ir.telefa.domain.News;
import ir.telefa.domain.NewsAgency;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public class Varzesh3FeedFetcherEJB {

    @EJB
    private ConfigurationEJB configurationEJB;

    @Inject
    private FeedFetcher feedFetcher;

    //@PersistenceContext
    //private EntityManager entityManager;

    private NewsAgency newsAgency;

    @PostConstruct
    public void init() {
        // newsAgency = (NewsAgency) entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency WHERE newsAgency.englishQualifiedName = 'varzesh3'").getSingleResult();
    }

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void fetchRSS() {
        try {
            List<FeedItem> feedItems = feedFetcher.fetch(configurationEJB.getVarzesh3RSSURL());
            Pattern pattern = Pattern.compile("itemid=(\\d+)");
            for (FeedItem feedItem : feedItems) {
                Matcher m = pattern.matcher(feedItem.getUri());
                if (m.find()) {
                    System.out.println(m.group(1));
                    /*String sourceId = m.group(1);
                    if (entityManager.createQuery("SELECT news FROM News news WHERE news.sourceId = ?")
                            .setParameter(1, sourceId).getResultList().size() == 0) {
                        News news = new News();
                        news.setAuthor(feedItem.getAuthor());
                        news.setFullDescription(null);
                        news.setImageData(null);
                        news.setLink(feedItem.getLink());
                        news.setLongDescription(feedItem.getDescription());
                        news.setNewsAgency(newsAgency);
                        news.setNumberOfVisits(0);
                        news.setPublishDate(new Date().getTime());
                        news.setQuestion(null);
                        news.setShortDescription(feedItem.getDescription());
                        news.setSourceId(sourceId);
                        news.setSourceLink(feedItem.getLink());
                        news.setSourcePublishDate(feedItem.getPublishDate());
                        news.setSourceURL(feedItem.getUri());
                        news.setTitle(feedItem.getTitle());
                        entityManager.persist(news);*/
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
