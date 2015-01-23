package ir.fanfoot.domain;

import ir.fanfoot.annotations.PersianNumbers;
import ir.fanfoot.annotations.Sorted;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "tag", schema = "public")
public class Tag {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    private UUID id;
    @PersianNumbers
    @Sorted(order = 1, sort = Sorted.Sort.ASCENDING)
    @Column(name = "name", length = 1024, nullable = false)
    private String name;
    @PersianNumbers
    @Column(name = "keywords", length = 5120, nullable = true)
    private String keywords;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim().toLowerCase();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Tag) {
            if (((Tag) obj).getId().equals(id)) {
                return true;
            }
            if (((Tag) obj).getName().trim().toLowerCase().equals(name.trim().toLowerCase())) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.trim().toLowerCase().hashCode();
    }
}
