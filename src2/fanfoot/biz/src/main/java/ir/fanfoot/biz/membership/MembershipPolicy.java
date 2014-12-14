package ir.fanfoot.biz.membership;

public interface MembershipPolicy {

    boolean allowsAuthentication(Credential credential);

    void submitAuthenticateRequest(Credential credential);

    void submitAuthenticationFailure(Credential credential);

    void submitAuthenticationSuccess(Credential credential);
}
