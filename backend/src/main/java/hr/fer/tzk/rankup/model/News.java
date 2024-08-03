package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "News")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNews")
    private Long id;

    @Column(name = "title", length = 80, nullable = false)
    private String title;

    @Column(name = "dateCreated", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "dateEdited")
    private LocalDate dateEdited;

    @Column(name = "myContent", length = 80, nullable = false)
    private String content;

    @Column(name = "images", length = 80)
    private String images;

    @ManyToOne
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @ManyToOne
    @JoinColumn(name = "idAuthor", nullable = false)
    private Member author;
}
