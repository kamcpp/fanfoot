package ir.fanfoot.biz.membership;


import org.labcrypto.membership.Credential;
import org.labcrypto.membership.MembershipPolicy;

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
