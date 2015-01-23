package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Profile;
import ir.fanfoot.util.i18n.StringHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(ProfileDAO.class)
public class ProfileDAOEJB extends AbstractDAO<Profile> implements ProfileDAO {

    public ProfileDAOEJB() {
        super(Profile.class);
    }

    @Override
    public Profile getByUsername(String username) {
        try {
            return (Profile) entityManager
                    .createQuery("SELECT e FROM " + getEntityName() + " e WHERE e.user.username = :username")
                    .setParameter("username", StringHelper.correctPersianCharacters(username))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Profile> getAllPagedBySearchText(int first, int pageSize, String searchText) {
        return null;
    }

    @Override
    public long countBySearchText(String searchText) {
        return 0;
    }
}
