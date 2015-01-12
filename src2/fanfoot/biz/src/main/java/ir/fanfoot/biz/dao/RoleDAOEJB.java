package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Role;

import javax.ejb.Local;
import javax.ejb.Stateless;

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
                    .setParameter("name", name.trim().toLowerCase())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
