package ir.fanfoot.domain;

import ir.fanfoot.annotations.EnglishNumbers;
import ir.fanfoot.annotations.PersianNumbers;
import ir.fanfoot.annotations.Sorted;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
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
    @PersianNumbers
    @Column(name = "title", length = 1024, nullable = false)
    private String title;
    @EnglishNumbers
    @Column(name = "source_id", length = 512, nullable = true)
    private String sourceId;
    @PersianNumbers
    @Column(name = "short_description", length = 2048, nullable = true)
    private String shortDescription;
    @PersianNumbers
    @Column(name = "full_description", length = 204800, nullable = true)
    private String fullDescription;
    @Column(name = "author", length = 1024, nullable = true)
    private String author;
    @Sorted(order = 1, sort = Sorted.Sort.DESCENDING)
    @Column(name = "source_publish_date", nullable = true)
    private long sourcePublishDate;
    @Column(name = "publish_date", nullable = false)
    private long publishDate;
    @Column(name = "number_of_visitors", nullable = false)
    private int numberOfVisits;
    @EnglishNumbers
    @Column(name = "source_link", length = 2048, nullable = true)
    private String sourceLink;
    @EnglishNumbers
    @Column(name = "image_file_extension", length = 64, nullable = true)
    private String imageFileExtension;
    @EnglishNumbers
    @Column(name = "video_file_extension", length = 64, nullable = true)
    private String videoFileExtension;
    @EnglishNumbers
    @Column(name = "image_link", length = 2048, nullable = true)
    private String imageLink;
    @EnglishNumbers
    @Column(name = "video_link", length = 2048, nullable = true)
    private String videoLink;
    @Column(name = "has_image", nullable = false)
    private boolean hasImage;
    @Column(name = "has_video", nullable = false)
    private boolean hasVideo;
    @Column(name = "shown", nullable = false)
    private boolean shown;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "news_agency_id", nullable = false)
    private NewsAgency newsAgency;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = true)
    private Question question;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "news__tag",
            schema = "public",
            joinColumns = @JoinColumn(name = "news_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "tag_id", nullable = false))
    private Set<Tag> tags;

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

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
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

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getImageFileExtension() {
        return imageFileExtension;
    }

    public void setImageFileExtension(String imageFileExtension) {
        this.imageFileExtension = imageFileExtension;
    }

    public String getVideoFileExtension() {
        return videoFileExtension;
    }

    public void setVideoFileExtension(String videoFileExtension) {
        this.videoFileExtension = videoFileExtension;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
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

    public Set<Tag> getTags() {
        if (tags == null) {
            tags = new HashSet<>();
        }
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Transient
    public String getFanfootImageLink() {
        return "/files/images/" + getImageFileName() + "?" + new Random().nextLong();
    }

    @Transient
    public String getFanfootImageLinkByWidth(int width) {
        return "/files/images/" + getImageFileNameByWidth(width) + "?" + new Random().nextLong();
    }

    @Transient
    public String getImageFileName() {
        return id + "." + imageFileExtension;
    }

    @Transient
    public String getImageFileNameByWidth(int width) {
        return id + "-" + width + "w." + imageFileExtension;
    }

    @Transient
    public String getImageFileNameByHeight(int height) {
        return id + "-" + height + "h." + imageFileExtension;
    }

    @Transient
    public String getImageFilePathByWidthAndHeight(int width, int height) {
        return id + "-" + width + "x" + height + "." + imageFileExtension;
    }
}
