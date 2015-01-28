package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Option;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(OptionDAO.class)
public class OptionDAOEJB extends AbstractDAO<Option> implements OptionDAO {

    public OptionDAOEJB() {
        super(Option.class);
    }

    @Override
    public List<Option> getAllPagedBySearchText(int first, int pageSize, String searchText) {
        return null;
    }

    @Override
    public long countBySearchText(String searchText) {
        return 0;
    }
}
