package ir.fanfoot.amdin_portal.jsfbeans;

import ir.fanfoot.biz.membership.*;
import ir.fanfoot.util.HashProvider;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean
public class LoginBean {

    private boolean rememberMe;
    private String username;
    private String password;

    @Inject
    private HashProvider hashProvider;

    @EJB
    private Membership membership;

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void processLogin() {
        try {
            Token token = membership.authenticate(
                    new UsernamePasswordCredential(username, hashProvider.hashAsString(password)));
            System.out.println("SUCCESSSSSS: " + token.value());
        } catch (InvalidCredentialException e) {
            e.printStackTrace();
        } catch (BadCredentialException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (MembershipPolicyException e) {
            e.printStackTrace();
        } catch (InvalidTokenException e) {
            e.printStackTrace();
        }
    }
}
