package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idnews")
    private Long id;

    @Column(name = "title", length = 80, nullable = false)
    private String title;

    @NotNull
    @Column(name = "datecreated", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreated;

    @Column(name = "dateedited")
    @Temporal(TemporalType.DATE)
    private LocalDate dateEdited;

    @Column(name = "mycontent", length = 80, nullable = false)
    private String content;

    @Column(name = "images", length = 80)
    private String images;

    @ManyToOne
    @JoinColumn(name = "idsection", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "idauthor", nullable = false)
    private Member author;
}
