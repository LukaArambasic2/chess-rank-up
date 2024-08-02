package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MySection", uniqueConstraints = @UniqueConstraint(columnNames = "nameSection"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSection")
    private Long id;

    @Column(name = "nameSection", nullable = false)
    private String name;

    @Column(name = "descriptionSection")
    private String description;

    @Column(name = "logo")
    private String logo;

    @Column(name = "isOpen")
    private boolean open = true;
}
