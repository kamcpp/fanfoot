package ir.telefa.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="site_user", schema = "public")
public class User {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;
    @Column(name="is_male", nullable = false)
    private boolean male;
    @Column(name="is_enabled", nullable =  false)
    private boolean enabled;
    @Column(name="is_expired", nullable =  false)
    private boolean expired;
    @Column(name="show_gender", nullable =  false)
    private boolean showGender;
    @Column(name="show_first_name", nullable =  false)
    private boolean showFirstName;
    @Column(name="show_middle_name", nullable =  false)
    private boolean showMiddleName;
    @Column(name="show_last_name", nullable =  false)
    private boolean showLastName;
    @Column(name="show_email", nullable =  false)
    private boolean showEmail;
    @Column(name="show_phone", nullable =  false)
    private boolean showPhone;
    @Column(name="show_cellphone", nullable =  false)
    private boolean showCellphone;
    @Column(name="show_address", nullable =  false)
    private boolean showAddress;
    @Column(name="show_second_email", nullable =  false)
    private boolean showSecondEmail;
    @Column(name="show_about_me", nullable =  false)
    private boolean showAboutMe;
    @Column(name="show_status", nullable =  false)
    private boolean showStatus;
    @Column(name="create_date", nullable =  false)
    private long createDate;
    @Column(name="last_login_date", nullable =  true)
    private Long lastLoginDate;
    @Column(name="title", length = 64, nullable =  false)
    private String title;
    @Column(name="first_name", length = 512, nullable =  false)
    private String firstName;
    @Column(name="middle_name", length = 512, nullable =  true)
    private String middleName;
    @Column(name="last_name", length = 512, nullable =  false)
    private String lastName;
    @Column(name="username", length = 20, nullable =  false, unique = true)
    private String username;
    @Column(name="email", length = 512, nullable =  false, unique = true)
    private String email;
    @Column(name="password_hash", length = 1024, nullable =  false)
    private String passwordHash;
    @Column(name="phone", length = 256, nullable =  true)
    private String phone;
    @Column(name="cellphone", length = 256, nullable =  true)
    private String cellphone;
    @Column(name="address", length = 2048, nullable =  true)
    private String address;
    @Column(name="second_email", length = 512, nullable =  true)
    private String secondEmail;
    @Column(name="about_me", length = 4096, nullable =  true)
    private String aboutMe;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name=  "sit_user__role",
            schema = "public",
            joinColumns = @JoinColumn(name="site_user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="role_id", nullable = false))
    private Set<Role> roles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
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

    public boolean isShowGender() {
        return showGender;
    }

    public void setShowGender(boolean showGender) {
        this.showGender = showGender;
    }

    public boolean isShowFirstName() {
        return showFirstName;
    }

    public void setShowFirstName(boolean showFirstName) {
        this.showFirstName = showFirstName;
    }

    public boolean isShowMiddleName() {
        return showMiddleName;
    }

    public void setShowMiddleName(boolean showMiddleName) {
        this.showMiddleName = showMiddleName;
    }

    public boolean isShowLastName() {
        return showLastName;
    }

    public void setShowLastName(boolean showLastName) {
        this.showLastName = showLastName;
    }

    public boolean isShowEmail() {
        return showEmail;
    }

    public void setShowEmail(boolean showEmail) {
        this.showEmail = showEmail;
    }

    public boolean isShowPhone() {
        return showPhone;
    }

    public void setShowPhone(boolean showPhone) {
        this.showPhone = showPhone;
    }

    public boolean isShowCellphone() {
        return showCellphone;
    }

    public void setShowCellphone(boolean showCellphone) {
        this.showCellphone = showCellphone;
    }

    public boolean isShowAddress() {
        return showAddress;
    }

    public void setShowAddress(boolean showAddress) {
        this.showAddress = showAddress;
    }

    public boolean isShowSecondEmail() {
        return showSecondEmail;
    }

    public void setShowSecondEmail(boolean showSecondEmail) {
        this.showSecondEmail = showSecondEmail;
    }

    public boolean isShowAboutMe() {
        return showAboutMe;
    }

    public void setShowAboutMe(boolean showAboutMe) {
        this.showAboutMe = showAboutMe;
    }

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Set<Role> getRoles() {
        if(roles == null) {
            roles = new HashSet<Role>();
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
