package ir.telefa.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "option", schema = "public")
public class Option {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;
    @Column(name = "local_text", length = 1024, nullable = false)
    private String localText;
    @Column(name = "english_text", length = 1024, nullable = true)
    private String englishText;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "site_user__option",
            schema = "public",
            joinColumns = @JoinColumn(name = "site_user_id", nullable = false, referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "option_id", nullable = false, referencedColumnName = "id"))
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<User>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
