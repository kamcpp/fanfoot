package ir.telefa.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name="profile", schema = "public")
public class UserProfile {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;
    @Column(name = "is_male", nullable = false)
    private boolean male;
    @Column(name = "show_gender", nullable = false)
    private boolean showGender;
    @Column(name = "show_first_name", nullable = false)
    private boolean showFirstName;
    @Column(name = "show_middle_name", nullable = false)
    private boolean showMiddleName;
    @Column(name = "show_last_name", nullable = false)
    private boolean showLastName;
    @Column(name = "show_email", nullable = false)
    private boolean showEmail;
    @Column(name = "show_phone", nullable = false)
    private boolean showPhone;
    @Column(name = "show_cellphone", nullable = false)
    private boolean showCellphone;
    @Column(name = "show_address", nullable = false)
    private boolean showAddress;
    @Column(name = "show_second_email", nullable = false)
    private boolean showSecondEmail;
    @Column(name = "show_about_me", nullable = false)
    private boolean showAboutMe;
    @Column(name = "show_status", nullable = false)
    private boolean showStatus;
    private long createDate;
    private Long lastEditDate;
    @Column(name = "title", length = 64, nullable = false)
    private String title;
    @Column(name = "first_name", length = 512, nullable = false)
    private String firstName;
    @Column(name = "middle_name", length = 512, nullable = true)
    private String middleName;
    @Column(name = "last_name", length = 512, nullable = false)
    private String lastName;

}
