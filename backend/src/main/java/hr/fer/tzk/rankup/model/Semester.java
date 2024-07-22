package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSemester")
    private Long id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nameSemester", nullable = false)
    private String name;

    @NotBlank
    @Temporal(TemporalType.DATE)
    @Column(name = "dateFromSemester", nullable = false)
    private LocalDate dateFrom;

    @NotBlank
    @Temporal(TemporalType.DATE)
    @Column(name = "dateToSemester", nullable = false)
    private LocalDate dateTo;

    public Semester() {}

    public Semester(String name, LocalDate dateFrom) {
        this.name = name;
        this.dateFrom = dateFrom;
    }

    public Semester(String name, LocalDate dateFrom, LocalDate dateTo) {
        if (dateFrom.isAfter(dateTo)) {
            throw new IllegalArgumentException("dateFrom must be after dateTo");
        }
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(@NotBlank LocalDate dateTo) {
        if (dateFrom.isAfter(dateTo)) {
            throw new IllegalArgumentException("dateFrom must be after dateTo");
        }
        this.dateTo = dateTo;
    }

    public @NotBlank LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(@NotBlank LocalDate dateFrom) {
        if (dateTo != null && dateFrom.isAfter(dateTo)) {
            throw new IllegalArgumentException("dateFrom must be after dateTo");
        }
        this.dateFrom = dateFrom;
    }

    public @NotBlank @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 30) String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(name, semester.name) && Objects.equals(dateFrom, semester.dateFrom) && Objects.equals(dateTo, semester.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateFrom, dateTo);
    }
}
