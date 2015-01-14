package ir.fanfoot.admin_portal.jsfbeans;

import ir.fanfoot.biz.dao.TeamDAO;
import ir.fanfoot.domain.Team;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.transaction.SystemException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@ManagedBean
@ViewScoped
public class TeamBean {

    @EJB
    private TeamDAO teamDAO;

    private Team team;

    public Team getTeam() {
        return team;
    }

    public void saveOrUpdate() throws SystemException {
        teamDAO.saveOrUpdate(team);
        RequestContext.getCurrentInstance().addCallbackParam("processed", true);
    }

    public void prepareForAdd() {
        if (team == null || team.getId() != null) {
            team = new Team();
        }
    }

    public void select(UUID id) {
        team = teamDAO.getById(id);
    }

    public void delete(UUID id) throws SystemException {
        teamDAO.delete(id);
    }

    public List<Team> getAll() {
        return teamDAO.getAll();
    }

    public void upload(FileUploadEvent event) {
        String filename = UUID.randomUUID() + "-" + UUID.randomUUID();
        String extension;
        switch (event.getFile().getContentType().toLowerCase()) {
            case "image/jpeg":
            case "image/jpg":
                extension = "jpg";
                break;
            case "image/png":
                extension = "png";
                break;
            default:
                throw new RuntimeException("Content type not supported.");
        }
        try {
            OutputStream out = new FileOutputStream("/home/kamran/.fanfoot/images/" + filename + "-original." + extension);
            IOUtils.copy(event.getFile().getInputstream(), out);
            out.close();
            team.setLogo(filename);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
