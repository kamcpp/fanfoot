package ir.fanfoot.amdin_portal.jsfbeans;

import ir.telefa.domain.NewsAgency;
import org.primefaces.context.RequestContext;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ManagedBean
@SessionScoped
public class NewsAgenciesBean {

    @PersistenceContext(unitName = "fanfoot")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private NewsAgency newsAgency;

    public NewsAgenciesBean() {
    }

    public NewsAgency getNewsAgency() {
        return newsAgency;
    }

    public void saveOrUpdateNewsAgency() throws SystemException {
        try {
            userTransaction.begin();
            if (newsAgency.getId() == null) {
                entityManager.persist(newsAgency);
            } else {
                entityManager.merge(newsAgency);
            }
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
        }
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (newsAgency == null || (newsAgency != null && newsAgency.getId() != null)) {
            newsAgency = new NewsAgency();
        }
    }

    public void select(UUID id) {
        newsAgency = (NewsAgency) entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency WHERE newsAgency.id = :id").setParameter("id", id).getSingleResult();
    }

    public void delete(UUID id) throws SystemException {
        NewsAgency newsAgency = (NewsAgency) entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency WHERE newsAgency.id = :id").setParameter("id", id).getSingleResult();
        System.out.println("------> " + id);
        try {
            userTransaction.begin();
            entityManager.createQuery("DELETE FROM NewsAgency newsAgency WHERE newsAgency.id = :id").setParameter("id", id).executeUpdate();
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
        }
    }

    public List getNewsAgencies() {
        return entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency ORDER BY newsAgency.englishQualifiedName").getResultList();
    }
}
