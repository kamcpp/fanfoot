package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Team;
import ir.fanfoot.util.i18n.StringHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(TeamDAO.class)
public class TeamDAOEJB extends AbstractDAO<Team> implements TeamDAO {

    public TeamDAOEJB() {
        super(Team.class);
    }

    @Override
    public Team getByEnglishName(String englishName) {
        try {
            return (Team) entityManager
                    .createQuery("SELECT e FROM " + getEntityName() + " e WHERE e.englishName = :englishName")
                    .setParameter("englishName", StringHelper.correctPersianCharacters(englishName.trim().toLowerCase()))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Team> getAllPagedBySearchText(int first, int pageSize, String searchText) {
        return null;
    }

    @Override
    public long countBySearchText(String searchText) {
        return 0;
    }
}
