package ir.fanfoot.domain;

import ir.fanfoot.annotations.EnglishNumbers;
import ir.fanfoot.annotations.PersianNumbers;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "site_user", schema = "public")
public class User {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;
    @Column(name = "is_expired", nullable = false)
    private boolean expired;
    @Column(name = "create_date", nullable = false)
    private long createDate;
    @Column(name = "last_login_date", nullable = true)
    private Long lastLoginDate;
    @EnglishNumbers
    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;
    @EnglishNumbers
    @Column(name = "email", length = 512, nullable = false, unique = true)
    private String email;
    @EnglishNumbers
    @Column(name = "password_hash", length = 1024, nullable = false)
    private String passwordHash;
    @PersianNumbers
    @Column(name = "phone", length = 256, nullable = true)
    private String phone;
    @PersianNumbers
    @Column(name = "cellphone", length = 256, nullable = true)
    private String cellphone;
    @PersianNumbers
    @Column(name = "address", length = 2048, nullable = true)
    private String address;
    @EnglishNumbers
    @Column(name = "second_email", length = 512, nullable = true)
    private String secondEmail;
    @PersianNumbers
    @Column(name = "about_me", length = 4096, nullable = true)
    private String aboutMe;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", nullable = true)
    private Team team;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Profile profile;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "site_user__role",
            schema = "public",
            joinColumns = @JoinColumn(name = "site_user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private Set<Role> roles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public void setSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Set<Role> getRoles() {
        if (roles == null) {
            roles = new HashSet<Role>();
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
