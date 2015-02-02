package ir.fanfoot.biz.membership;

import ir.fanfoot.biz.membership.FanFootRole;
import ir.fanfoot.domain.User;
import org.labcrypto.membership.Principal;
import org.labcrypto.membership.Role;

import java.util.ArrayList;
import java.util.List;

public class DefaultPrinciple implements Principal {

    private List<Role> roles;
    private User user;

    public DefaultPrinciple(User user) {
        setUser(user);
    }

    @Override
    public List<Role> getRoles() {
        return roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        roles = new ArrayList<>();
        for (ir.fanfoot.domain.Role domainRole : user.getRoles()) {
            roles.add(new FanFootRole(domainRole.getName()));
        }
    }
}
