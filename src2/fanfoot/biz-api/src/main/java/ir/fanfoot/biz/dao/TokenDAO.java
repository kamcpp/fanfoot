package ir.fanfoot.biz.dao;

import ir.fanfoot.domain.Token;

public interface TokenDAO extends GenericDAO<Token> {

    Token getByValue(String value);

    Token getActiveByUsername(String username);
}
