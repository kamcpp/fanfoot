package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Profile;

public interface ProfileDAO extends GenericDAO<Profile> {

    Profile getByUsername(String username);
}
