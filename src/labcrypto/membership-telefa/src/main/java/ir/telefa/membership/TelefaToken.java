package ir.telefa.membership;


import org.labcrypto.membership.Token;

import java.util.UUID;

public class TelefaToken implements Token {
    private String value;

    public TelefaToken() {
        value = UUID.randomUUID().toString() + "-" + UUID.randomUUID().toString();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
