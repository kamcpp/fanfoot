package ir.fanfoot.domain;

import ir.fanfoot.annotations.EnglishNumbers;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "token", schema = "public")
public class Token {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;
    @Column(name = "expired", nullable = false)
    private boolean expired;
    @Column(name = "disabled", nullable = false)
    private boolean disabled;
    @Column(name = "issue_date", nullable = false)
    private long issueDate;
    @Column(name = "expired_date", nullable = true)
    private Long expiredDate;
    @Column(name = "duration", nullable = false)
    private int duration;
    @EnglishNumbers
    @Column(name = "value", nullable = false)
    private String value;
    @EnglishNumbers
    @Column(name = "source", nullable = false)
    private String source;
    @Column(name = "reason", nullable = true)
    @EnglishNumbers
    private String reason;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_user_id", nullable = false)
    private User user;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public long getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(long issueDate) {
        this.issueDate = issueDate;
    }

    public Long getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Long expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
