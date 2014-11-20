package ir.telefa.membership;

import org.labcrypto.membership.Role;

public class TelefaRole implements Role {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
