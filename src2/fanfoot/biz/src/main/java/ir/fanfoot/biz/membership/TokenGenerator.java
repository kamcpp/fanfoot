package ir.fanfoot.biz.membership;

public interface TokenGenerator {
    Token generate(Credential credential, Principal principal);
}
