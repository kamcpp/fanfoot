package ir.fanfoot.amdin_portal.jsfbeans;

import ir.fanfoot.biz.dao.TagDAO;
import ir.fanfoot.domain.Tag;
import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import java.util.List;
import java.util.UUID;

@ManagedBean
@SessionScoped
public class TagBean {

    @EJB
    private TagDAO tagDAO;

    private Tag tag;

    public Tag getTag() {
        return tag;
    }

    public void saveOrUpdate() throws SystemException {
        tagDAO.saveOrUpdate(tag);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (tag == null || (tag != null && tag.getId() != null)) {
            tag = new Tag();
        }
    }

    public void select(UUID id) {
        tag = tagDAO.getById(id);
    }

    public void delete(UUID id) throws SystemException {
        tagDAO.delete(id);
    }

    public List<Tag> getAll() {
        return tagDAO.getAll();
    }
}
