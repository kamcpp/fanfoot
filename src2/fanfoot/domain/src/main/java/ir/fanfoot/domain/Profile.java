package ir.fanfoot.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name="profile", schema = "public")
public class Profile {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;
    @Column(name = "gender", nullable = false)
    private int gender;
    @Column(name = "gender_shown", nullable = false)
    private boolean genderShown;
    @Column(name = "first_name_shown", nullable = false)
    private boolean firstNameShown;
    @Column(name = "middle_name_shown", nullable = false)
    private boolean middleNameShown;
    @Column(name = "last_name_shown", nullable = false)
    private boolean lastNameShown;
    @Column(name = "email_shown", nullable = false)
    private boolean emailShown;
    @Column(name = "phone_shown", nullable = false)
    private boolean phoneShown;
    @Column(name = "cellphone_shown", nullable = false)
    private boolean cellphoneShown;
    @Column(name = "address_shown", nullable = false)
    private boolean addressShown;
    @Column(name = "second_email_shown", nullable = false)
    private boolean secondEmailShown;
    @Column(name = "about_me_shown", nullable = false)
    private boolean aboutMeShown;
    @Column(name = "status_shown", nullable = false)
    private boolean statusShown;
    @Column(name = "status", nullable = false)
    private int status;
    @Column(name = "create_date", nullable = false)
    private long createDate;
    @Column(name = "last_edit_date", nullable = true)
    private Long lastEditDate;
    @Column(name = "title", length = 64, nullable = false)
    private String title;
    @Column(name = "first_name", length = 512, nullable = false)
    private String firstName;
    @Column(name = "middle_name", length = 512, nullable = true)
    private String middleName;
    @Column(name = "last_name", length = 512, nullable = false)
    private String lastName;
    @Column(name = "status_message", length = 1024, nullable = false)
    private String statusMessage;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Transient
    public boolean isMale() {
        return this.gender == 1;
    }

    @Transient
    public boolean isFemale() {
        return this.gender == 0;
    }

    public boolean isGenderShown() {
        return genderShown;
    }

    public void setGenderShown(boolean genderShown) {
        this.genderShown = genderShown;
    }

    public boolean isFirstNameShown() {
        return firstNameShown;
    }

    public void setFirstNameShown(boolean firstNameShown) {
        this.firstNameShown = firstNameShown;
    }

    public boolean isMiddleNameShown() {
        return middleNameShown;
    }

    public void setMiddleNameShown(boolean middleNameShown) {
        this.middleNameShown = middleNameShown;
    }

    public boolean isLastNameShown() {
        return lastNameShown;
    }

    public void setLastNameShown(boolean lastNameShown) {
        this.lastNameShown = lastNameShown;
    }

    public boolean isEmailShown() {
        return emailShown;
    }

    public void setEmailShown(boolean emailShown) {
        this.emailShown = emailShown;
    }

    public boolean isPhoneShown() {
        return phoneShown;
    }

    public void setPhoneShown(boolean phoneShown) {
        this.phoneShown = phoneShown;
    }

    public boolean isCellphoneShown() {
        return cellphoneShown;
    }

    public void setCellphoneShown(boolean cellphoneShown) {
        this.cellphoneShown = cellphoneShown;
    }

    public boolean isAddressShown() {
        return addressShown;
    }

    public void setAddressShown(boolean addressShown) {
        this.addressShown = addressShown;
    }

    public boolean isSecondEmailShown() {
        return secondEmailShown;
    }

    public void setSecondEmailShown(boolean secondEmailShown) {
        this.secondEmailShown = secondEmailShown;
    }

    public boolean isAboutMeShown() {
        return aboutMeShown;
    }

    public void setAboutMeShown(boolean aboutMeShown) {
        this.aboutMeShown = aboutMeShown;
    }

    public boolean isStatusShown() {
        return statusShown;
    }

    public void setStatusShown(boolean statusShown) {
        this.statusShown = statusShown;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public Long getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Long lastEditDate) {
        this.lastEditDate = lastEditDate;
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

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
