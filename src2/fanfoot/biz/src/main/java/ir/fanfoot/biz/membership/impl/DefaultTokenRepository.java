package ir.fanfoot.biz.membership.impl;

import ir.fanfoot.biz.dao.TokenDAO;
import ir.fanfoot.biz.membership.FanFootToken;
import ir.fanfoot.biz.membership.Token;
import ir.fanfoot.biz.membership.TokenNotFoundException;
import ir.fanfoot.biz.membership.TokenRepository;

import javax.ejb.EJB;
import java.util.Date;

public class DefaultTokenRepository implements TokenRepository {

    @EJB
    private TokenDAO tokenDAO;

    @Override
    public Token findByValue(String value) throws TokenNotFoundException {
        try {
            ir.fanfoot.domain.Token domainToken = tokenDAO.getByValue(value);
            if (domainToken == null) {
                throw new TokenNotFoundException("There is no token with value: '" + value + "'");
            }
            FanFootToken fanFootToken = new FanFootToken();
            fanFootToken.setDisabled(domainToken.isDisabled());
            fanFootToken.setDuration(domainToken.getDuration());
            fanFootToken.setExpired(domainToken.isExpired());
            fanFootToken.setIssueDate(domainToken.getIssueDate());
            fanFootToken.setValue(domainToken.getValue());
            return fanFootToken;
        } catch (Exception e) {
            throw new TokenNotFoundException(e);
        }

    }

    @Override
    public void expireToken(Token token) throws TokenNotFoundException {
        try {
            ir.fanfoot.domain.Token domainToken = tokenDAO.getByValue(token.value());
            if (domainToken == null) {
                throw new TokenNotFoundException("There is no token with value: '" + token.value() + "'");
            }
            domainToken.setExpired(true);
            domainToken.setExpiredDate(new Date().getTime());
            tokenDAO.saveOrUpdate(domainToken);
        } catch (Exception e) {
            throw new TokenNotFoundException(e);
        }
    }

    @Override
    public void disableToken(Token token) throws TokenNotFoundException {
        try {
            ir.fanfoot.domain.Token domainToken = tokenDAO.getByValue(token.value());
            if (domainToken == null) {
                throw new TokenNotFoundException("There is no token with value: '" + token.value() + "'");
            }
            domainToken.setDisabled(true);
            tokenDAO.saveOrUpdate(domainToken);
        } catch (Exception e) {
            throw new TokenNotFoundException(e);
        }
    }
}
