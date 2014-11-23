package ir.fanfoot.biz.dao;

import ir.telefa.domain.Tag;

public interface TagDAO extends GenericDAO<Tag> {

    Tag getByName(String name);
}
