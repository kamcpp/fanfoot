package ir.telefa.membership;

import org.labcrypto.membership.Token;

public interface TokenFactory {
    Token create();
}