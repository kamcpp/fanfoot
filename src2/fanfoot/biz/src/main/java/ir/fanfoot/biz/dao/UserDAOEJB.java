package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.User;

import org.labcrypto.util.i18n.StringHelper;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(UserDAO.class)
public class UserDAOEJB extends AbstractDAO<User> implements UserDAO {

    @EJB
    private RoleDAO roleDAO;

    public UserDAOEJB() {
        super(User.class);
    }

    @Override
    public User getByUsername(String username) {
        try {
            return (User) entityManager
                    .createQuery("SELECT e FROM " + getEntityName() + " e WHERE e.username = :username")
                    .setParameter("username", StringHelper.correctPersianCharacters(username))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAllPagedBySearchText(int first, int pageSize, String searchText) {
        return null;
    }

    @Override
    public long countBySearchText(String searchText) {
        return 0;
    }
}
