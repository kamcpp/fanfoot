package ir.fanfoot.biz.membership;

import org.labcrypto.membership.*;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Local(Membership.class)
public class MembershipEJB implements Membership {

    @Inject
    private MembershipPolicy membershipPolicy;
    @Inject
    private PrincipleRepository principleRepository;
    @Inject
    private TokenGenerator tokenGenerator;
    @Inject
    private TokenRepository tokenRepository;

    private Membership membership;

    @PostConstruct
    private void postConstruct() {
        membership = new LabCryptoMembership(principleRepository,
                tokenGenerator,
                tokenRepository,
                membershipPolicy);
    }

    @Override
    public Token authenticate(Credential credential) throws InvalidCredentialException, BadCredentialException, ServerException, MembershipPolicyException, InvalidTokenException {
        return membership.authenticate(credential);
    }

    @Override
    public void validateToken(Token token) throws InvalidTokenException, ExpiredTokenException, ServerException {
        membership.validateToken(token);
    }

    @Override
    public void disableToken(Token token) throws InvalidTokenException, ExpiredTokenException, ServerException {
        membership.disableToken(token);
    }

    @Override
    public List<Role> getRoles(Token token) throws InvalidTokenException, ExpiredTokenException, ServerException, TokenNotFoundException {
        return membership.getRoles(token);
    }
}
