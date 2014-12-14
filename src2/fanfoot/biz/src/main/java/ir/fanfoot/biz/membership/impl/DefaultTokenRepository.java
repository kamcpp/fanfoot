package ir.fanfoot.biz.membership.impl;

import ir.fanfoot.biz.membership.Token;
import ir.fanfoot.biz.membership.TokenNotFoundException;
import ir.fanfoot.biz.membership.TokenRepository;

public class DefaultTokenRepository implements TokenRepository {

    @Override
    public Token findByValue(String value) throws TokenNotFoundException {
        return null;
    }

    @Override
    public void expireToken(Token token) throws TokenNotFoundException {

    }

    @Override
    public void disableToken(Token token) throws TokenNotFoundException {

    }
}
