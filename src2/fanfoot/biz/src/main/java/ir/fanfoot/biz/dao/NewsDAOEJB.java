package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.News;
import ir.fanfoot.domain.NewsAgency;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(NewsDAO.class)
public class NewsDAOEJB extends AbstractDAO<News> implements NewsDAO {

    public NewsDAOEJB() {
        super(News.class);
    }

    @Override
    public long countShown() {
        return (Long) entityManager
                .createQuery("SELECT COUNT(news.id) FROM News news WHERE news.shown = true")
                .getSingleResult();
    }

    @Override
    public List<News> getAllShownPaged(int first, int pageSize) {
        return (List<News>) entityManager.createQuery("SELECT news FROM News news WHERE news.shown = true " + getDefaultOrderByClause())
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public boolean sourceIdExists(NewsAgency newsAgency, String sourceId) {
        return ((Long) entityManager.createQuery("SELECT COUNT(news.id) FROM News news WHERE news.sourceId = :sourceId AND news.newsAgency.id = :newsAgencyId")
                .setParameter("sourceId", sourceId)
                .setParameter("newsAgencyId", newsAgency.getId())
                .getSingleResult()) > 0;
    }
}
