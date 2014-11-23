package ir.fanfoot.biz.dao;

import ir.telefa.domain.Tag;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
@Local(TagDAO.class)
public class TagDAOEJB implements TagDAO {

    @PersistenceContext(unitName = "fanfoot")
    private EntityManager entityManager;

    @Override
    public UUID saveOrUpdate(Tag tag) {
        if(tag.getId() == null) {
            entityManager.persist(tag);
        } else {
            entityManager.merge(tag);
        }
        return tag.getId();
    }

    @Override
    public void delete(UUID id) {
        entityManager.createQuery("DELETE FROM Tag tag WHERE tag.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public List<Tag> getAll() {
        return (List<Tag>) entityManager.createQuery("SELECT tag FROM Tag tag ORDER BY tag.name").getResultList();
    }

    @Override
    public Tag getById(UUID id) {
        try {
            return (Tag) entityManager.createQuery("SELECT tag FROM Tag tag WHERE tag.id = :id").setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Tag getByName(String name) {
        try {
            return (Tag) entityManager.createQuery("SELECT tag FROM Tag tag WHERE tag.name = :name").setParameter("name", name.trim().toLowerCase()).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
