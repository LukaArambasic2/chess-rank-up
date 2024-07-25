package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInfo")
    private Long id;

    @NotBlank
    @Size(max = 40)
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

    public MemberInfo() {}

    public MemberInfo(Long id, String stringValue, Section section, Attribute attribute, Member member) {
        this.id = id;
        this.stringValue = stringValue;
        this.section = section;
        this.attribute = attribute;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 40) String getStringValue() {
        return stringValue;
    }

    public void setStringValue(@NotBlank @Size(max = 40) String stringValue) {
        this.stringValue = stringValue;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "MemberInfo{" +
                "id=" + id +
                ", stringValue='" + stringValue + '\'' +
                ", section=" + section +
                ", attribute=" + attribute +
                ", member=" + member +
                '}';
    }


}
