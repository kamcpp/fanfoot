package ir.fanfoot.admin_portal.jsfbeans;

import ir.fanfoot.biz.dao.OptionDAO;
import ir.fanfoot.biz.dao.QuestionDAO;
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
public class QuestionBean {

    @EJB
    private QuestionDAO questionDAO;

    @EJB
    private OptionDAO optionDAO;

    private Question question;
    private LazyDataModel<Question> dataModel;
    private String searchText;

    public Question getQuestion() {
        return question;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public LazyDataModel<Question> getDataModel() {
        return dataModel;
    }

    public void setDataModel(LazyDataModel<Question> dataModel) {
        this.dataModel = dataModel;
    }

    @PostConstruct
    public void init() {
        dataModel = new LazyDataModel<Question>() {
            @Override
            public List<Question> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Question> allPaged = questionDAO.getAllPaged(first, pageSize);
                setRowCount((int) questionDAO.count());
                return allPaged;
            }
        };
    }

    public void saveOrUpdate() throws SystemException {
        questionDAO.saveOrUpdate(question);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (question == null || question.getId() != null) {
            question = new Question();
        }
    }

    public void select(UUID id) {
        question = questionDAO.getById(id);
    }

    public void delete(UUID id) {
        questionDAO.delete(id);
    }

    public void search() {
        dataModel = new LazyDataModel<Question>() {
            @Override
            public List<Question> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Question> allPaged = questionDAO.getAllPagedBySearchText(first, pageSize, searchText);
                setRowCount((int) questionDAO.countBySearchText(searchText));
                return allPaged;
            }
        };
    }
}
