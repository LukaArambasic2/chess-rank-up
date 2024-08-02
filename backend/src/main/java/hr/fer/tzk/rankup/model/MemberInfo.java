package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MemberInfo", uniqueConstraints = {@UniqueConstraint(columnNames = {"idSection", "idMember", "idAttribute"}),})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInfo")
    private Long id;

    @Column(nullable = false)
    private String stringValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idSection", nullable = false)
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idAttribute", nullable = false)
    private Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idMember", nullable = false)
    private Member member;
}
