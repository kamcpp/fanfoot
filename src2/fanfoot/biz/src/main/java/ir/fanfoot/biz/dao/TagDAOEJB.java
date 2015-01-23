package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Tag;
import ir.fanfoot.util.i18n.StringHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

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
                    .setParameter("name", StringHelper.correctPersianCharacters(name.trim().toLowerCase()))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Tag> getAllPagedBySearchText(int first, int pageSize, String searchText) {
        return (List<Tag>) entityManager
                .createQuery("SELECT tag FROM Tag tag WHERE (tag.name LIKE :searchText OR tag.keywords LIKE :searchText) " + getDefaultOrderByClause())
                .setParameter("searchText", "%" + StringHelper.correctPersianCharacters(searchText) + "%")
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    @Override
    public long countBySearchText(String searchText) {
        return (Long) entityManager
                .createQuery("SELECT COUNT(tag.id) FROM Tag tag WHERE (tag.name LIKE :searchText OR tag.keywords LIKE :searchText)")
                .setParameter("searchText", "%" + StringHelper.correctPersianCharacters(searchText) + "%")
                .getSingleResult();
    }
}
