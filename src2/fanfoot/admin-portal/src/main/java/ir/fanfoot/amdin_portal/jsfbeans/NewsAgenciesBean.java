package ir.fanfoot.amdin_portal.jsfbeans;

import ir.telefa.domain.NewsAgency;
import org.primefaces.context.RequestContext;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class NewsAgenciesBean {

    @PersistenceContext(unitName = "fanfoot")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private UUID id;
    private String localName;
    private String englishName;
    private String qualifiedEnglishName;
    private String website;
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getQualifiedEnglishName() {
        return qualifiedEnglishName;
    }

    public void setQualifiedEnglishName(String qualifiedEnglishName) {
        this.qualifiedEnglishName = qualifiedEnglishName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addNewsAgency() throws SystemException, NotSupportedException {
        NewsAgency newsAgency = new NewsAgency();
        newsAgency.setDescription(description);
        newsAgency.setEnglishName(englishName);
        newsAgency.setEnglishQualifiedName(qualifiedEnglishName);
        newsAgency.setLocalName(localName);
        newsAgency.setWebsite(website);
        try {
            userTransaction.begin();
            entityManager.persist(newsAgency);
            userTransaction.commit();
        } catch (Exception e) {
            userTransaction.rollback();
        }
        RequestContext.getCurrentInstance().addCallbackParam("added", true);
    }

    public void select(UUID id) {
        NewsAgency newsAgency = (NewsAgency) entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency WHERE newsAgency.id = ?").setParameter(1, id).getSingleResult();
        this.id = id;
        this.description = newsAgency.getDescription();
        this.englishName = newsAgency.getEnglishName();
        this.localName = newsAgency.getLocalName();
        this.qualifiedEnglishName = newsAgency.getEnglishQualifiedName();
        this.website = newsAgency.getWebsite();
    }

    public List getNewsAgencies() {
        return entityManager.createQuery("SELECT newsAgency FROM NewsAgency newsAgency").getResultList();
    }
}
