package ir.fanfoot.biz.membership.impl;

import ir.fanfoot.biz.membership.FanFootRole;
import ir.fanfoot.biz.membership.Principal;
import ir.fanfoot.biz.membership.Role;
import ir.fanfoot.domain.User;

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
