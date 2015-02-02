package ir.fanfoot.biz.membership;

import ir.fanfoot.biz.dao.TokenDAO;
import ir.fanfoot.biz.dao.UserDAO;
import ir.fanfoot.domain.User;
import org.labcrypto.membership.*;

import javax.ejb.EJB;

public class DefaultPrincipleRepository implements PrincipleRepository {

    @EJB
    private UserDAO userDAO;

    @EJB
    private TokenDAO tokenDAO;

    @Override
    public void check(Principal principal)
            throws InvalidPrincipleException, PrincipleNotFoundException {
        if (principal instanceof DefaultPrinciple) {
            try {
                if (userDAO.getByUsername(((DefaultPrinciple) principal).getUser().getUsername()) == null) {
                    throw new PrincipleNotFoundException("Username does not exist.");
                }
                return;
            } catch (Exception e) {
                throw new PrincipleNotFoundException(e);
            }
        }
        throw new InvalidPrincipleException("Principle is not supported.");
    }

    @Override
    public Principal findByCredential(Credential credential)
            throws InvalidCredentialException, PrincipleNotFoundException {
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential usernamePasswordCredential = (UsernamePasswordCredential) credential;
            try {
                User user = userDAO.getByUsername(usernamePasswordCredential.getUsername());
                if (user == null) {
                    return null;
                }
                if (user.getPasswordHash().equals(usernamePasswordCredential.getPasswordHash())) {
                    return new DefaultPrinciple(user);
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        throw new InvalidCredentialException("Credential is not supported.");
    }

    @Override
    public boolean hasActiveToken(Principal principal)
            throws InvalidPrincipleException, InvalidCredentialException, PrincipleNotFoundException {
        return getActiveToken(principal) != null;
    }

    @Override
    public Token getActiveToken(Principal principal)
            throws InvalidPrincipleException, InvalidCredentialException, PrincipleNotFoundException {
        check(principal);
        DefaultPrinciple defaultPrinciple = (DefaultPrinciple) principal;
        ir.fanfoot.domain.Token domainToken = tokenDAO.getActiveByUsername(defaultPrinciple.getUser().getUsername());
        if (domainToken == null) {
            return null;
        }
        FanFootToken fanFootToken = new FanFootToken();
        fanFootToken.setDisabled(domainToken.isDisabled());
        fanFootToken.setDuration(domainToken.getDuration());
        fanFootToken.setExpired(domainToken.isExpired());
        fanFootToken.setIssueDate(domainToken.getIssueDate());
        fanFootToken.setValue(domainToken.getValue());
        return fanFootToken;
    }

    @Override
    public void registerToken(Principal principal, Token token)
            throws InvalidPrincipleException, InvalidCredentialException, InvalidTokenException, PrincipleNotFoundException {
        check(principal);
        if (token instanceof FanFootToken) {
            DefaultPrinciple defaultPrinciple = (DefaultPrinciple) principal;
            FanFootToken fanFootToken = (FanFootToken) token;
            ir.fanfoot.domain.Token domainToken = new ir.fanfoot.domain.Token();
            domainToken.setDisabled(fanFootToken.disabled());
            domainToken.setDuration(fanFootToken.duration());
            domainToken.setExpired(fanFootToken.expired());
            domainToken.setExpiredDate(null);
            domainToken.setIssueDate(fanFootToken.issueDate());
            domainToken.setReason("");
            domainToken.setSource("");
            domainToken.setValue(fanFootToken.value());
            domainToken.setUser(userDAO.getById(defaultPrinciple.getUser().getId()));
            tokenDAO.saveOrUpdate(domainToken);
            return;
        }
        throw new InvalidTokenException("Token is not supported.");
    }

    @Override
    public Principal findByToken(Token token)
            throws InvalidTokenException, TokenNotFoundException {
        if (token instanceof FanFootToken) {
            throw new InvalidTokenException("Token is not supported.");
        }
        ir.fanfoot.domain.Token domainToken = tokenDAO.getByValue(token.value());
        if (domainToken == null) {
            throw new TokenNotFoundException("Token not found with value: '" + token.value() + "'");
        }
        return new DefaultPrinciple(domainToken.getUser());
    }
}
