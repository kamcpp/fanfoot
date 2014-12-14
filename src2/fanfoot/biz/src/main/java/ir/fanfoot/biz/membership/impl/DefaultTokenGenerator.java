package ir.fanfoot.biz.membership.impl;

import ir.fanfoot.biz.membership.Credential;
import ir.fanfoot.biz.membership.Principal;
import ir.fanfoot.biz.membership.Token;
import ir.fanfoot.biz.membership.TokenGenerator;

public class DefaultTokenGenerator implements TokenGenerator {
    @Override
    public Token generate(Credential credential, Principal user) {
        return null;
    }
}
