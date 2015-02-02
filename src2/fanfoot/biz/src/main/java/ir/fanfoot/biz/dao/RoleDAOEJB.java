package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Role;

import org.labcrypto.util.i18n.StringHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import java.util.List;

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

    @Override
    public List<Role> getAllPagedBySearchText(int first, int pageSize, String searchText) {
        return null;
    }

    @Override
    public long countBySearchText(String searchText) {
        return 0;
    }
}
