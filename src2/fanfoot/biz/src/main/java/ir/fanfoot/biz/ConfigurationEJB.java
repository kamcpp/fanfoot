package ir.fanfoot.biz;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import java.io.File;

@Singleton
@LocalBean
public class ConfigurationEJB {

    @PostConstruct
    public void init() {
        makeDirectories();
    }

    public String getVarzesh3RSSURL() {
        return "http://www.varzesh3.com/rss/rss.xml";
    }

    public String getBaseDownloadPath() {
        return "/home/kamran/fanfoot/";
    }

    private void makeDirectories() {
        File f = new File(getBaseDownloadPath() + "images");
        if (!f.exists()) {
            if (!f.mkdirs()) {
                throw new RuntimeException("Images directory could be made at path: '" + f.getAbsolutePath() + "'.");
            }
        }
        f = new File(getBaseDownloadPath() + "videos");
        if (!f.exists()) {
            if (!f.mkdirs()) {
                throw new RuntimeException("Videos directory could be made at path: '" + f.getAbsolutePath() + "'.");
            }
        }
    }
}
