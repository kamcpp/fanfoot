package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Tag;

public interface TagDAO extends GenericDAO<Tag> {

    Tag getByName(String name);
}
