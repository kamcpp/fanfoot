package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Question;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(QuestionDAO.class)
public class QuestionDAOEJB extends AbstractDAO<Question> implements QuestionDAO {

    public QuestionDAOEJB() {
        super(Question.class);
    }

    @Override
    public List<Question> getAllPagedBySearchText(int first, int pageSize, String searchText) {
        return null;
    }

    @Override
    public long countBySearchText(String searchText) {
        return 0;
    }
}
