package ir.fanfoot.biz.membership.impl;

import ir.fanfoot.biz.membership.*;

public class DefaultPrincipleRepository implements PrincipleRepository {

    @Override
    public Principal findByCredential(Credential credential) throws InvalidCredentialException, PrincipleNotFoundException {
        return null;
    }

    @Override
    public boolean hasActiveToken(Principal principal) throws InvalidCredentialException, PrincipleNotFoundException {
        return false;
    }

    @Override
    public Token getActiveToken(Principal principal) throws InvalidCredentialException, PrincipleNotFoundException {
        return null;
    }

    @Override
    public void registerToken(Principal principal, Token token) throws InvalidCredentialException, PrincipleNotFoundException {

    }

    @Override
    public Principal findByToken(Token token) throws InvalidTokenException, PrincipleNotFoundException {
        return null;
    }
}
