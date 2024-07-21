package hr.fer.tzk.rankup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Semester")
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSemester;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    public Semester(Long idSemester, String name, Date dateFrom, Date dateTo) {
        this.idSemester = idSemester;
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Semester() {}

    public Long getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(Long idSemester) {
        this.idSemester = idSemester;
    }

    public @NotBlank @Size(max = 30) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 30) String name) {
        this.name = name;
    }

    public @NotBlank Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(@NotBlank Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public @NotBlank Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(@NotBlank Date dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "idSemester=" + idSemester +
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
