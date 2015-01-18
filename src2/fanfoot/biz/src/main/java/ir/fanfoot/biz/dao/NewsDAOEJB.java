package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.News;
import ir.fanfoot.domain.NewsAgency;
import ir.fanfoot.util.StringHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;
import java.util.UUID;

@Stateless
@Local(NewsDAO.class)
public class NewsDAOEJB extends AbstractDAO<News> implements NewsDAO {

    public NewsDAOEJB() {
        super(News.class);
    }

    @Override
    public long countShown() {
        return (Long) entityManager
                .createQuery("SELECT COUNT(news.id) FROM News news WHERE news.shown = TRUE")
                .getSingleResult();
    }

    @Override
    public long countByTitle(String searchText) {
        return (Long) entityManager
                .createQuery("SELECT COUNT(news.id) FROM News news WHERE news.shown = TRUE AND (news.title LIKE :searchText OR news.shortDescription LIKE :searchText)")
                .setParameter("searchText", "%" + StringHelper.correctPersianCharacters(searchText) + "%")
                .getSingleResult();
    }

    @Override
    public List<News> getAllShownPaged(int first, int pageSize) {
        return (List<News>) entityManager
                .createQuery("SELECT news FROM News news WHERE news.shown = TRUE " + getDefaultOrderByClause())
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<News> getAllPagedByTitle(int first, int pageSize, String searchText) {
        return (List<News>) entityManager
                .createQuery("SELECT news FROM News news WHERE (news.title LIKE :searchText OR news.shortDescription LIKE :searchText) " + getDefaultOrderByClause())
                .setParameter("searchText", "%" + StringHelper.correctPersianCharacters(searchText) + "%")
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<News> getAllPagedShownByTitle(int first, int pageSize, String searchText) {
        return (List<News>) entityManager
                .createQuery("SELECT news FROM News news WHERE news.shown = true AND news.title LIKE :searchText " + getDefaultOrderByClause())
                .setParameter("searchText", "%" + StringHelper.correctPersianCharacters(searchText) + "%")
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public boolean sourceIdExists(NewsAgency newsAgency, String sourceId) {
        return ((Long) entityManager.createQuery("SELECT COUNT(news.id) FROM News news WHERE news.sourceId = :sourceId AND news.newsAgency.id = :newsAgencyId")
                .setParameter("sourceId", StringHelper.correctPersianCharacters(sourceId))
                .setParameter("newsAgencyId", newsAgency.getId())
                .getSingleResult()) > 0;
    }

    @Override
    public void hide(UUID id) {
        News news = getById(id);
        news.setShown(false);
        saveOrUpdate(news);
    }

    @Override
    public void show(UUID id) {
        News news = getById(id);
        news.setShown(true);
        saveOrUpdate(news);
    }
}
