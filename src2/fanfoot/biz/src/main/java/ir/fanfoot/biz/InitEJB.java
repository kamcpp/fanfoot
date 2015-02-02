package ir.fanfoot.biz;

import ir.fanfoot.biz.dao.*;
import ir.fanfoot.domain.*;
import org.labcrypto.util.crypto.HashProvider;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Date;

@Singleton
@Startup
public class InitEJB {

    @EJB
    private NewsAgencyDAO newsAgencyDAO;

    @EJB
    private UserDAO userDAO;

    @EJB
    private RoleDAO roleDAO;

    @EJB
    private TeamDAO teamDAO;

    @EJB
    private ProfileDAO profileDAO;

    @EJB
    private TagDAO tagDAO;

    @Inject
    private HashProvider hashProvider;

    @PostConstruct
    public void init() {
        Role adminUserRole;
        Role normalUserRole;
        Team adminTeam;
        if (newsAgencyDAO.getByEnglishQualifiedName("varzesh3") == null) {
            NewsAgency newsAgency = new NewsAgency();
            newsAgency.setDescription("");
            newsAgency.setEnglishName("Varzesh3");
            newsAgency.setEnglishQualifiedName("varzesh3");
            newsAgency.setLocalName("ورزش ۳");
            newsAgency.setWebsite("http://www.varzesh3.com");
            newsAgencyDAO.saveOrUpdate(newsAgency);
        }
        if ((adminUserRole = roleDAO.getByName("ADMIN_USER_ROLE")) == null) {
            adminUserRole = new Role();
            adminUserRole.setName("ADMIN_USER_ROLE");
            roleDAO.saveOrUpdate(adminUserRole);
        }
        if ((normalUserRole = roleDAO.getByName("NORMAL_USER_ROLE")) == null) {
            normalUserRole = new Role();
            normalUserRole.setName("NORMAL_USER_ROLE");
            roleDAO.saveOrUpdate(normalUserRole);
        }
        if ((adminTeam = teamDAO.getByEnglishName("admin-team")) == null) {
            adminTeam = new Team();
            adminTeam.setDescription("DESCRIPTION");
            adminTeam.setEnglishName("admin-team");
            adminTeam.setEstablishmentYear("1980");
            adminTeam.setLocalName("admin-team");
            teamDAO.saveOrUpdate(adminTeam);
        }
        if (userDAO.getByUsername("admin") == null) {
            User adminUser = new User();
            adminUser.setAboutMe("ABOUT_ME");
            adminUser.setAddress("ADDRESS");
            adminUser.setCellphone("CELLPHONE");
            adminUser.setCreateDate(new Date().getTime());
            adminUser.setEmail("admin@fanfoot.ir");
            adminUser.setEnabled(true);
            adminUser.setExpired(false);
            adminUser.setLastLoginDate(null);
            adminUser.setPasswordHash(hashProvider.hashAsString("admin"));
            adminUser.setPhone("PHONE");
            adminUser.setSecondEmail("SECOND_EMAIL");
            adminUser.setTeam(adminTeam);
            adminUser.setUsername("admin");
            adminUser.getRoles().add(adminUserRole);
            adminUser.getRoles().add(normalUserRole);
            userDAO.saveOrUpdate(adminUser);
            Profile profile = new Profile();
            profile.setAboutMeShown(true);
            profile.setAddressShown(true);
            profile.setCellphoneShown(true);
            profile.setCreateDate(new Date().getTime());
            profile.setEmailShown(true);
            profile.setFirstName("ADMIN");
            profile.setFirstNameShown(true);
            profile.setGender(1);
            profile.setGenderShown(true);
            profile.setLastEditDate(null);
            profile.setLastName("ADMIN");
            profile.setLastNameShown(true);
            profile.setMiddleName("");
            profile.setMiddleNameShown(true);
            profile.setPhoneShown(true);
            profile.setSecondEmailShown(true);
            profile.setStatus(0);
            profile.setStatusMessage("");
            profile.setStatusShown(true);
            profile.setTitle("MR.");
            profile.setUser(adminUser);
            profileDAO.saveOrUpdate(profile);
        }
        if (tagDAO.getByName("مپرسپولیس") == null) {
            Tag tag = new Tag();
            tag.setName("مپرسپولیس");
            tag.setKeywords("پرسپولیس");
            tagDAO.saveOrUpdate(tag);
        }
    }
}
