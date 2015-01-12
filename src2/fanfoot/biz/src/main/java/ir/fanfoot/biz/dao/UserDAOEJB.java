package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.User;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

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
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
