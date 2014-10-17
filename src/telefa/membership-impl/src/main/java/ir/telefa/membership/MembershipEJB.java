package ir.telefa.membership;

import ir.telefa.membership.cdi.Auditable;
import ir.telefa.membership.cdi.Loggable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Set;

@Loggable
@Auditable
@Stateless
public class MembershipEJB implements MembershipLocal, MembershipRemote {
    @Inject
    private TokenFactory tokenFactory;
    @Inject
    private TokenRepository tokenRepository;
    @Inject
    private UserRepository userRepository;

    @Override
    public Token authenticate(Credential credential) throws MembershipException {
        return null;
    }

    @Override
    public void validateToken(Token token) throws MembershipException {

    }

    @Override
    public void invalidateToken(Token token) throws MembershipException {

    }

    @Override
    public Set<Role> getRoles(Credential credential) throws MembershipException {
        return null;
    }

    @Override
    public Set<Role> getRoles(Token token) throws MembershipException {
        return null;
    }

    @Override
    public User getUser(Credential credential) throws MembershipException {
        return null;
    }

    @Override
    public User getUser(Token token) throws MembershipException {
        return null;
    }
}
