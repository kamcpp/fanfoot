package ir.fanfoot.admin_portal.jsfbeans;

import ir.fanfoot.biz.dao.TagDAO;
import ir.fanfoot.domain.News;
import ir.fanfoot.domain.Tag;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ManagedBean
@SessionScoped
public class TagBean {

    @EJB
    private TagDAO tagDAO;

    private Tag tag;
    private LazyDataModel<Tag> dataModel;
    private String searchText;

    public Tag getTag() {
        return tag;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public LazyDataModel<Tag> getDataModel() {
        return dataModel;
    }

    public void setDataModel(LazyDataModel<Tag> dataModel) {
        this.dataModel = dataModel;
    }

    @PostConstruct
    public void init() {
        dataModel = new LazyDataModel<Tag>() {
            @Override
            public List<Tag> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Tag> allPaged = tagDAO.getAllPaged(first, pageSize);
                setRowCount((int) tagDAO.count());
                return allPaged;
            }
        };
    }

    public void saveOrUpdate() throws SystemException {
        tagDAO.saveOrUpdate(tag);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (tag == null || tag.getId() != null) {
            tag = new Tag();
        }
    }

    public void select(UUID id) {
        tag = tagDAO.getById(id);
    }

    public void delete(UUID id) throws SystemException {
        tagDAO.delete(id);
    }

    public void search() {
        dataModel = new LazyDataModel<Tag>() {
            @Override
            public List<Tag> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Tag> allPaged = tagDAO.getAllPagedBySearchText(first, pageSize, searchText);
                setRowCount((int) tagDAO.countBySearchText(searchText));
                return allPaged;
            }
        };
    }
}
