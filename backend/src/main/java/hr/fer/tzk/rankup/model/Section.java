package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

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
