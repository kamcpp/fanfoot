package org.labcrypto.membership;

import java.util.Set;

public interface MembershipLocal {
    Token authenticate(Credential credential) throws MembershipException;

    void validateToken(Token token) throws MembershipException;

    void invalidateToken(Token token) throws MembershipException;

    Set<Role> getRoles(Credential credential) throws MembershipException;

    Set<Role> getRoles(Token token) throws MembershipException;

    User getUser(Credential credential) throws MembershipException;

    User getUser(Token token) throws  MembershipException;
}
