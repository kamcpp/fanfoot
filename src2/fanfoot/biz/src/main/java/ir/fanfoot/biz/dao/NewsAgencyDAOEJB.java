package ir.fanfoot.biz.dao;

import ir.telefa.domain.NewsAgency;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
@Local(NewsAgencyDAO.class)
public class NewsAgencyDAOEJB implements NewsAgencyDAO {

    @PersistenceContext(unitName = "fanfoot")
    private EntityManager entityManager;

    @Override
    public UUID saveOrUpdate(NewsAgency newsAgency) {
        if (newsAgency.getId() == null) {
            entityManager.persist(newsAgency);
        } else {
            entityManager.merge(newsAgency);
        }
        return newsAgency.getId();
    }

    @Override
    public void delete(UUID id) {
        entityManager.createQuery("DELETE FROM NewsAgency newsAgency WHERE newsAgency.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public List<NewsAgency> getAll() {
        return (List<NewsAgency>) entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency ORDER BY newsAgency.englishQualifiedName").getResultList();
    }

    @Override
    public NewsAgency getById(UUID id) {
        try {
            return (NewsAgency) entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency WHERE newsAgency.id = :id").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public NewsAgency getByEnglishQualifiedName(String englishQualifiedName) {
        try {
            return (NewsAgency) entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency WHERE newsAgency.englishQualifiedName = :eqn").setParameter("eqn", englishQualifiedName).getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }
}
