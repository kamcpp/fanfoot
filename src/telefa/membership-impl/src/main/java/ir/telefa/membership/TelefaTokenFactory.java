package ir.telefa.membership;

public class TelefaTokenFactory implements TokenFactory {
    @Override
    public Token create() {
        return new TelefaToken();
    }
}
