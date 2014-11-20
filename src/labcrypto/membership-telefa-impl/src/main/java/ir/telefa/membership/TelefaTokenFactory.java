package ir.telefa.membership;

import org.labcrypto.membership.Token;

public class TelefaTokenFactory implements TokenFactory {
    @Override
    public Token create() {
        return new TelefaToken();
    }
}
