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
@Table(name = "question", schema = "public")
public class Question {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;
    @PersianNumbers
    @Column(name = "local_text", length = 2048, nullable = false)
    @EnglishNumbers
    private String localText;
    @Column(name = "english_text", length = 2048, nullable = true)
    private String englishText;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Option> options;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "site_user__question", schema = "public",
            joinColumns = @JoinColumn(name = "site_user_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", nullable = false, referencedColumnName = "id"))
    private Set<User> users;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLocalText() {
        return localText;
    }

    public void setLocalText(String localText) {
        this.localText = localText;
    }

    public String getEnglishText() {
        return englishText;
    }

    public void setEnglishText(String englishText) {
        this.englishText = englishText;
    }

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }

    public Set<User> getUsers() {
        if(users == null) {
            users = new HashSet<User>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
