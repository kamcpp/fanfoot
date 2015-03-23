package ir.fanfoot.admin_portal.jsfbeans;

import ir.fanfoot.biz.dao.OptionDAO;
import ir.fanfoot.domain.Option;
import ir.fanfoot.domain.Question;
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

    public Option getOption() {
        return option;
    }

    public void saveOrUpdate() throws SystemException {
        optionDAO.saveOrUpdate(option);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd(Question question) {
        if (option == null || option.getId() != null) {
            option = new Option();
            option.setQuestion(question);

        }
    }

    public void select(UUID id) {
        option = optionDAO.getById(id);
    }

    public void delete(UUID id) {
        optionDAO.delete(id);
    }
}
