package ir.fanfoot.admin_portal.jsfbeans;

import ir.fanfoot.biz.dao.RoleDAO;
import ir.fanfoot.domain.Role;
import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.SystemException;
import java.util.List;
import java.util.UUID;

@ManagedBean
@SessionScoped
public class RoleBean {

    @EJB
    private RoleDAO roleDAO;

    private Role role;

    public Role getRole() {
        return role;
    }

    public void saveOrUpdate() throws SystemException {
        roleDAO.saveOrUpdate(role);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (role == null || role.getId() != null) {
            role = new Role();
        }
    }

    public void select(UUID id) {
        role = roleDAO.getById(id);
    }

    public void delete(UUID id) throws SystemException {
        roleDAO.delete(id);
    }

    public List<Role> getAll() {
        return roleDAO.getAll();
    }
}
