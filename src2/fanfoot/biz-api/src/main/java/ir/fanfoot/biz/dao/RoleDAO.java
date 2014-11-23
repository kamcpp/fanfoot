package ir.fanfoot.biz.dao;

import ir.telefa.domain.Role;

import java.util.List;

public interface RoleDAO extends GenericDAO<Role> {

    List<Role> getByUsername(String username);
}
