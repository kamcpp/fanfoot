package ir.fanfoot.util.atom;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import ir.fanfoot.util.atom.FeedFetcher;
import ir.fanfoot.util.atom.FeedItem;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ROMEFeedFetcher implements FeedFetcher {

    @Override
    public List<FeedItem> fetch(String feedUrl) throws IOException, FeedException {
        URL url = new URL(feedUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(httpURLConnection));
        List entries = feed.getEntries();
        List<FeedItem> feedItems = new ArrayList<FeedItem>();
        for (Object entry : entries) {
            if (entry instanceof SyndEntry) {
                SyndEntry syndEntry = (SyndEntry) entry;
                FeedItem feedItem = new FeedItem();
                feedItem.setAuthor(syndEntry.getAuthor());
                feedItem.setDescription(syndEntry.getDescription().getValue());
                feedItem.setLink(syndEntry.getLink());
                feedItem.setPublishDate(syndEntry.getPublishedDate() != null ? syndEntry.getPublishedDate().getTime() : 0);
                feedItem.setSource(syndEntry.getSource() != null ? syndEntry.getSource().getLink() : null);
                feedItem.setTitle(syndEntry.getTitle());
                feedItem.setUri(syndEntry.getUri());
                feedItems.add(feedItem);
            }
        }
        httpURLConnection.disconnect();
        return feedItems;
    }
}
