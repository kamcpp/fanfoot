package ir.fanfoot.biz;

import ir.fanfoot.biz.dao.NewsAgencyDAO;
import ir.telefa.domain.NewsAgency;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class InitEJB {

    @EJB
    private NewsAgencyDAO newsAgencyDAO;

    @PostConstruct
    public void init() {
        if (newsAgencyDAO.getByEnglishQualifiedName("varzesh3") == null) {
            NewsAgency newsAgency = new NewsAgency();
            newsAgency.setDescription("");
            newsAgency.setEnglishName("Varzesh3");
            newsAgency.setEnglishQualifiedName("varzesh3");
            newsAgency.setLocalName("ورزش ۳");
            newsAgency.setWebsite("http://www.varzesh3.com");
            newsAgencyDAO.saveOrUpdate(newsAgency);
        }
    }
}
