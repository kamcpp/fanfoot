package ir.fanfoot.util;

import com.sun.syndication.io.FeedException;

import java.io.IOException;
import java.util.List;

public interface FeedFetcher {
    List<FeedItem> fetch(String feedUrl) throws IOException, FeedException;
}
