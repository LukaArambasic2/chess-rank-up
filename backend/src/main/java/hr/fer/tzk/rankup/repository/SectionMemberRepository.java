package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.SectionMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface SectionMemberRepository extends JpaRepository<SectionMember, Long> {
    
    List<SectionMember> findAllBySection_Id(Long idSection);
    List<SectionMember> findAllByMember_Id(Long idMember);

    Optional<SectionMember> findSectionMemberByMember_IdAndSection_Id(Long idMember, Long idSection);
}
