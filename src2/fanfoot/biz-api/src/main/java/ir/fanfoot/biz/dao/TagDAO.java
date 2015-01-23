package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Tag;

import java.util.List;

public interface TagDAO extends GenericDAO<Tag> {

    Tag getByName(String name);
}
