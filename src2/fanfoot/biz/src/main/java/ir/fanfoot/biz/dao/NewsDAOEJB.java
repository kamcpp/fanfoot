package ir.fanfoot.biz.dao;

import ir.telefa.domain.News;
import ir.telefa.domain.NewsAgency;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
@Local(NewsDAO.class)
public class NewsDAOEJB implements NewsDAO {

    @PersistenceContext(unitName = "fanfoot")
    private EntityManager entityManager;

    @Override
    public UUID saveOrUpdate(News news) {
        if (news.getId() == null) {
            entityManager.persist(news);
        } else {
            entityManager.merge(news);
        }
        return news.getId();
    }

    @Override
    public void delete(UUID id) {
        entityManager.createQuery("DELETE FROM News news WHERE news.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public News getById(UUID id) {
        try {
            return (News) entityManager.createQuery("SELECT news FROM News news WHERE news.id = :id").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<News> getAll() {
        return (List<News>) entityManager.createQuery("SELECT news FROM News news ORDER BY news.publishDate DESC").getResultList();
    }

    @Override
    public long count() {
        return (Long) entityManager.createQuery("SELECT COUNT(news.id) FROM News news").getSingleResult();
    }

    @Override
    public long countShown() {
        return (Long) entityManager.createQuery("SELECT COUNT(news.id) FROM News news WHERE news.shown = true").getSingleResult();
    }

    @Override
    public List<News> getAllPaged(int first, int pageSize) {
        return (List<News>) entityManager.createQuery("SELECT news FROM News news ORDER BY news.publishDate DESC")
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public List<News> getAllShownPaged(int first, int pageSize) {
        return (List<News>) entityManager.createQuery("SELECT news FROM News news WHERE news.shown = true ORDER BY news.publishDate DESC")
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
