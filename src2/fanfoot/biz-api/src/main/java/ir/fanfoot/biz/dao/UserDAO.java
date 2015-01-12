package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.User;

public interface UserDAO extends GenericDAO<User> {

    User getByUsername(String username);
}
