package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "myattribute")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idattribute")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameattribute", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(name = "dattype", nullable = false)
    private String dataType;
}
