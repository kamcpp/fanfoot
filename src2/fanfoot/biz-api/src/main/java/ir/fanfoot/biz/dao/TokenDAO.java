package ir.fanfoot.biz.dao;

import ir.telefa.domain.Token;

public interface TokenDAO extends GenericDAO<Token> {

    Token getByValue(String value);
}
