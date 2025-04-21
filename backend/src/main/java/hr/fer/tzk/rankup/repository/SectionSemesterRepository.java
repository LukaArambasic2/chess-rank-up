package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.SectionSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface SectionSemesterRepository extends JpaRepository<SectionSemester, Long> {

    Optional<SectionSemester> findByMember_IdAndSection_IdAndSemester_Id(Long idMember, Long idSection, Long idSemester);

    List<SectionSemester> findBySection_IdAndSemester_Id(Long idSection, Long idSemester);
}
