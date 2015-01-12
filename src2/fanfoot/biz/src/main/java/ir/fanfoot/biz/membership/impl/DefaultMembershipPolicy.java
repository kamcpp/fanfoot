package ir.fanfoot.biz.membership.impl;

import ir.fanfoot.biz.membership.Credential;
import ir.fanfoot.biz.membership.MembershipPolicy;

public class DefaultMembershipPolicy implements MembershipPolicy {

    @Override
    public boolean allowsAuthentication(Credential credential) {
        return true;
    }

    @Override
    public void submitAuthenticateRequest(Credential credential) {

    }

    @Override
    public void submitAuthenticationFailure(Credential credential) {

    }

    @Override
    public void submitAuthenticationSuccess(Credential credential) {

    }
}
