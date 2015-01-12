package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Tag;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local(TagDAO.class)
public class TagDAOEJB extends AbstractDAO<Tag> implements TagDAO {

    public TagDAOEJB() {
        super(Tag.class);
    }

    @Override
    public Tag getByName(String name) {
        try {
            return (Tag) entityManager
                    .createQuery("SELECT tag FROM Tag tag WHERE tag.name = :name")
                    .setParameter("name", name.trim().toLowerCase())
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
