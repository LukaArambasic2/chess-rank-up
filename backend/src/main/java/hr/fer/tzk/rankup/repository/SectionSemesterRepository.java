package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.SectionSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionSemesterRepository extends JpaRepository<SectionSemester, Long> {

    Optional<SectionSemester> findByMember_IdAndSection_IdAndSemester_Id(Long idMember, Long idSection, Long idSemester);

    List<SectionSemester> findBySection_IdAndSemester_Id(Long idSection, Long idSemester);
}
