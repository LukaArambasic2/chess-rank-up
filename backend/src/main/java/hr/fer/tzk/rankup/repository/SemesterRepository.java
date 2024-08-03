package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester, Long> {

    Optional<Semester> findByName(String name);

    @Query("SELECT s FROM Semester s WHERE :date BETWEEN s.dateFrom AND s.dateTo")
    Optional<Semester> findSemesterByDate(@Param("date") LocalDate date);

    @Query("SELECT s FROM Semester s ORDER BY s.dateTo DESC")
    List<Semester> findAllSemestersOrderedByDateToDesc();
}
