package ir.fanfoot.biz.membership;

public interface PrincipleRepository {

    void check(Principal principal) throws InvalidPrincipleException, PrincipleNotFoundException;

    Principal findByCredential(Credential credential) throws
            InvalidCredentialException, PrincipleNotFoundException;

    boolean hasActiveToken(Principal principal) throws
            InvalidPrincipleException, InvalidCredentialException, PrincipleNotFoundException;


    Token getActiveToken(Principal principal) throws
            InvalidPrincipleException, InvalidCredentialException, PrincipleNotFoundException;

    void registerToken(Principal principal, Token token) throws
            InvalidPrincipleException, InvalidCredentialException, InvalidTokenException, PrincipleNotFoundException;

    Principal findByToken(Token token) throws
            InvalidTokenException, TokenNotFoundException;
}
