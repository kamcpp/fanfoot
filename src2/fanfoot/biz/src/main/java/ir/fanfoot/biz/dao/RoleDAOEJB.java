package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Role;
import ir.fanfoot.util.StringHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

@Stateless
@Local(RoleDAO.class)
public class RoleDAOEJB extends AbstractDAO<Role> implements RoleDAO {

    public RoleDAOEJB() {
        super(Role.class);
    }

    @Override
    public Role getByName(String name) {
        try {
            return (Role) entityManager
                    .createQuery("SELECT e FROM " + getEntityName() + " e WHERE e.name = :name")
                    .setParameter("name", StringHelper.correctPersianCharacters(name))
                    .getSingleResult();
        } catch (Exception e) {
            if (!(e instanceof NoResultException)) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
