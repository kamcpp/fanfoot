package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Role;

import java.util.List;

public interface RoleDAO extends GenericDAO<Role> {

    Role getByName(String name);
}
