package ir.fanfoot.admin_portal.jsfbeans;

import ir.fanfoot.biz.membership.*;
import ir.fanfoot.util.crypto.HashProvider;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
            HttpSession session = (HttpSession) FacesContext.
                    getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("authToken", token);
            FacesContext.getCurrentInstance().getExternalContext().redirect("u/news");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login");
            } catch (IOException e1) {
            }
        }
    }
}
