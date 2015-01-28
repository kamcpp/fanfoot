package ir.fanfoot.admin_portal.jsfbeans;

import ir.fanfoot.biz.dao.OptionDAO;
import ir.fanfoot.domain.Option;
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
public class OptionBean {

    @EJB
    private OptionDAO optionDAO;

    private Option option;
    private LazyDataModel<Option> dataModel;
    private String searchText;

    public Option getOption() {
        return option;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public LazyDataModel<Option> getDataModel() {
        return dataModel;
    }

    public void setDataModel(LazyDataModel<Option> dataModel) {
        this.dataModel = dataModel;
    }

    @PostConstruct
    public void init() {
        dataModel = new LazyDataModel<Option>() {
            @Override
            public List<Option> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Option> allPaged = optionDAO.getAllPaged(first, pageSize);
                setRowCount((int) optionDAO.count());
                return allPaged;
            }
        };
    }

    public void saveOrUpdate() throws SystemException {
        optionDAO.saveOrUpdate(option);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (option == null || option.getId() != null) {
            option = new Option();
        }
    }

    public void select(UUID id) {
        option = optionDAO.getById(id);
    }

    public void delete(UUID id) {
        optionDAO.delete(id);
    }

    public void search() {
        dataModel = new LazyDataModel<Option>() {
            @Override
            public List<Option> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Option> allPaged = optionDAO.getAllPagedBySearchText(first, pageSize, searchText);
                setRowCount((int) optionDAO.countBySearchText(searchText));
                return allPaged;
            }
        };
    }
}
