package ir.fanfoot.admin_portal.jsfbeans;

import ir.fanfoot.biz.dao.NewsDAO;
import ir.fanfoot.biz.dao.TagDAO;
import ir.fanfoot.domain.Tag;
import ir.fanfoot.util.JalaliCalendar;
import ir.fanfoot.domain.News;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import java.util.*;

@ManagedBean
@SessionScoped
public class NewsBean {

    @EJB
    private NewsDAO newsDAO;
    @EJB
    private TagDAO tagDAO;
    private News news;
    private LazyDataModel<News> dataModel;
    private String searchText;

    public News getNews() {
        return news;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @PostConstruct
    public void init() {
        dataModel = new LazyDataModel<News>() {
            @Override
            public List<News> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<News> allPaged = newsDAO.getAllPaged(first, pageSize);
                setRowCount((int) newsDAO.count());
                return allPaged;
            }
        };
    }

    public long getNumberOfShownNews() {
        return newsDAO.countShown();
    }

    public void saveOrUpdate() throws SystemException {
        newsDAO.saveOrUpdate(news);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (news == null || news.getId() != null) {
            news = new News();
        }
    }

    public void select(UUID id) {
        news = newsDAO.getById(id);
    }

    public void delete(UUID id) throws SystemException {
        newsDAO.delete(id);
    }

    public LazyDataModel<News> getDataModel() {
        return dataModel;
    }

    public void setDataModel(LazyDataModel<News> dataModel) {
        this.dataModel = dataModel;
    }

    public String convertTimeWithTimeZome(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
        cal.setTimeInMillis(time);
        return JalaliCalendar.gregorianToJalali(new JalaliCalendar.YearMonthDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH))).toString() + " - " +
                cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
    }

    public void search() {
        dataModel = new LazyDataModel<News>() {
            @Override
            public List<News> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<News> allPaged = newsDAO.getAllPagedByTitle(first, pageSize, searchText);
                setRowCount((int) newsDAO.countByTitle(searchText));
                return allPaged;
            }
        };
    }

    public void hide(UUID id) {
        newsDAO.hide(id);
    }

    public void show(UUID id) {
        newsDAO.show(id);
    }

    public String getTags() {
        String result = "";
        if (news != null) {
            Set<Tag> tags = news.getTags();
            String delimiter = "";
            for (Tag tag : tags) {
                result += delimiter + tag.getName();
                delimiter = "-";
            }
        }
        return result;
    }

    public void setTags(String text) {
        String[] tokens = text.split("-");
        news.getTags().clear();
        for (String token : tokens) {
            String tagName = token.trim().toLowerCase();
            if (tagName.length() > 0) {
                Tag tag = tagDAO.getByName(tagName);
                if (tag == null) {
                    Tag newTag = new Tag();
                    newTag.setName(tagName);
                    tagDAO.saveOrUpdate(newTag);
                    tag = newTag;
                }
                news.getTags().add(tag);
            }
        }
    }
}
