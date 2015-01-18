package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.NewsAgency;
import ir.fanfoot.util.StringHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local(NewsAgencyDAO.class)
public class NewsAgencyDAOEJB extends AbstractDAO<NewsAgency> implements NewsAgencyDAO {

    public NewsAgencyDAOEJB() {
        super(NewsAgency.class);
    }

    @Override
    public NewsAgency getByEnglishQualifiedName(String englishQualifiedName) {
        try {
            return (NewsAgency) entityManager
                    .createQuery("SELECT newsAgency FROM NewsAgency newsAgency WHERE newsAgency.englishQualifiedName = :eqn")
                    .setParameter("eqn", StringHelper.correctPersianCharacters(englishQualifiedName))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
