package ir.fanfoot.amdin_portal.jsfbeans;

import ir.fanfoot.biz.dao.NewsAgencyDAO;
import ir.telefa.domain.NewsAgency;
import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import java.util.List;
import java.util.UUID;

@ManagedBean
@SessionScoped
public class NewsAgencyBean {

    @EJB
    private NewsAgencyDAO newsAgencyDAO;

    private NewsAgency newsAgency;

    public NewsAgencyBean() {
    }

    public NewsAgency getNewsAgency() {
        return newsAgency;
    }

    public void saveOrUpdate() throws SystemException {
        newsAgencyDAO.saveOrUpdate(newsAgency);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (newsAgency == null || (newsAgency != null && newsAgency.getId() != null)) {
            newsAgency = new NewsAgency();
        }
    }

    public void select(UUID id) {
        newsAgency = newsAgencyDAO.getById(id);
    }

    public void delete(UUID id) throws SystemException {
        newsAgencyDAO.delete(id);
    }

    public List<NewsAgency> getAll() {
        return newsAgencyDAO.getAll();
    }
}
