package ir.fanfoot.admin_portal.jsfbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@ManagedBean
public class LogoutBean {
    public void logout() {
        HttpSession session = (HttpSession) FacesContext.
                getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("authToken");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
