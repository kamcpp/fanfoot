package ir.fanfoot.biz.membership;

public interface TokenRepository {

    Token findByValue(String value) throws TokenNotFoundException;

    void expireToken(Token token) throws TokenNotFoundException;

    void disableToken(Token token) throws TokenNotFoundException;
}
