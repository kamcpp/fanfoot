package ir.fanfoot.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "news_agency", schema = "public")
public class NewsAgency {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(name = "english_name", length = 256, nullable = false)
    private String englishName;
    @Column(name = "english_qualified_name", length = 512, nullable = true)
    private String englishQualifiedName;
    @Column(name = "local_name", length = 512, nullable = false)
    private String localName;
    @Column(name = "website", length = 1024, nullable = true)
    private String website;
    @Column(name = "description", length = 5120, nullable = true)
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getEnglishQualifiedName() {
        return englishQualifiedName;
    }

    public void setEnglishQualifiedName(String englishQualifiedName) {
        this.englishQualifiedName = englishQualifiedName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
