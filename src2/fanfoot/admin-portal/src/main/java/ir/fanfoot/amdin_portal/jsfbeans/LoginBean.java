package ir.fanfoot.amdin_portal.jsfbeans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LoginBean {

    private boolean rememberMe;
    private String username;
    private String password;

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

    }
}
