package ir.fanfoot.biz;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class ConfigurationEJB {
    public String getVarzesh3RSSURL() {
        return "http://www.varzesh3.com/rss/rss.xml";
    }
}
