package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "News")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNews")
    private Long id;

    @NotBlank
    @Size(max = 80)
    @Column(name = "title", length = 80, nullable = false)
    private String title;

    @NotNull
    @Column(name = "dateCreated", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "dateEdited")
    private LocalDate dateEdited;

    @NotBlank
    @Size(max = 80)
    @Column(name = "content", length = 80, nullable = false)
    private String content;

    @Size(max = 80)
    @Column(name = "images", length = 80)
    private String images;

    @ManyToOne
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "idAuthor", nullable = false)
    private Member author;

    public News() { }

    public News(Long id, String title, LocalDate dateCreated, LocalDate dateEdited, String content, String images, Section section, Member author) {
        this.id = id;
        this.title = title;
        this.dateCreated = dateCreated;
        this.dateEdited = dateEdited;
        this.content = content;
        this.images = images;
        this.section = section;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(LocalDate dateEdited) {
        this.dateEdited = dateEdited;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }
}
