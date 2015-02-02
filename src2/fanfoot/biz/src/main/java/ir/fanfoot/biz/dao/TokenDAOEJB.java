package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Token;

import org.labcrypto.util.i18n.StringHelper;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Local(TokenDAO.class)
public class TokenDAOEJB extends AbstractDAO<Token> implements TokenDAO {

    public TokenDAOEJB() {
        super(Token.class);
    }

    @Override
    public Token getByValue(String value) {
        try {
            return (Token) entityManager
                    .createQuery("SELECT e FROM " + getEntityName() + " e WHERE e.value = :value")
                    .setParameter("value", StringHelper.correctPersianCharacters(value))
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Token getActiveByUsername(String username) {
        try {
            return (Token) entityManager
                    .createQuery("SELECT e FROM " + getEntityName() + " e " +
                            "WHERE e.user.username = :username " +
                            "AND e.disabled = false " +
                            "AND e.expired = false " +
                            "ORDER BY e.issueDate DESC")
                    .setParameter("username", StringHelper.correctPersianCharacters(username))
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Token> getAllPagedBySearchText(int first, int pageSize, String searchText) {
        return null;
    }

    @Override
    public long countBySearchText(String searchText) {
        return 0;
    }
}
