package ir.telefa.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "news", schema = "public")
public class News {
    @Id
    @NotNull
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(columnDefinition = "uuid")
    private UUID id;
    @Column(name = "title", length = 1024, nullable = false)
    private String title;
    @Column(name = "source_id", length = 512, nullable = true)
    private String sourceId;
    @Column(name = "short_description", length = 2048, nullable = true)
    private String shortDescription;
    @Column(name = "long_description", length = 4096, nullable = true)
    private String longDescription;
    @Column(name = "full_description", length = 204800, nullable = true)
    private String fullDescription;
    @Column(name = "source_url", length = 2048, nullable = true)
    private String sourceURL;
    @Column(name = "author", length = 1024, nullable = true)
    private String author;
    @Column(name = "source_publish_date", nullable = true)
    private long sourcePublishDate;
    @Column(name = "publish_date", nullable = false)
    private long publishDate;
    @Column(name = "number_of_visitors", nullable = false)
    private int numberOfVisits;
    @Column(name = "link", length = 2048, nullable = true)
    private String link;
    @Column(name = "source_link", length = 2048, nullable = true)
    private String sourceLink;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "image_data", nullable = true)
    private byte[] imageData;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_agency_id", nullable = false)
    private NewsAgency newsAgency;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = true)
    private Question question;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getSourcePublishDate() {
        return sourcePublishDate;
    }

    public void setSourcePublishDate(long sourcePublishDate) {
        this.sourcePublishDate = sourcePublishDate;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public int getNumberOfVisits() {
        return numberOfVisits;
    }

    public void setNumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public NewsAgency getNewsAgency() {
        return newsAgency;
    }

    public void setNewsAgency(NewsAgency newsAgency) {
        this.newsAgency = newsAgency;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
