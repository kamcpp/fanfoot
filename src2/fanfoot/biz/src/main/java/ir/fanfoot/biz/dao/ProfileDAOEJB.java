package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Profile;

import javax.ejb.Local;
import javax.ejb.Stateless;

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
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
