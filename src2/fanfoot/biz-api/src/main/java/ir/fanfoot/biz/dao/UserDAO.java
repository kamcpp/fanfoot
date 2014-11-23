package ir.fanfoot.biz.dao;

import ir.telefa.domain.User;

public interface UserDAO extends GenericDAO<User> {

    User getByUsername(String username);

    User getByUsernameAndPasswordHash(String username, String passwordHash);
}
