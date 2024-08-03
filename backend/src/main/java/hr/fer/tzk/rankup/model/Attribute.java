package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MyAttribute")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAttribute")
    private Long id;

    @Column(name = "nameAttribute", nullable = false)
    private String name;

    @Column(name = "datType", nullable = false)
    private String dataType;
}
