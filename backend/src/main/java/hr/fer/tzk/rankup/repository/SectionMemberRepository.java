package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.dto.SectionMemberGetDto;
import hr.fer.tzk.rankup.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionMemberRepository extends JpaRepository<Section, Long> {

    @Query("SELECT new hr.fer.tzk.rankup.dto.SectionMemberGetDto(m.id, m.firstName, m.lastName, m.jmbag, m.email, sm.active, sm.pointsAll, r.name) " +
            "FROM SectionMember sm " +
            "JOIN sm.member m " +
            "JOIN sm.section s " +
            "JOIN sm.rank r " +
            "WHERE s.id = :idSection")
    List<SectionMemberGetDto> findAllSectionMembersBySectionId(@Param("idSection") Long idSection);

    @Query("SELECT new hr.fer.tzk.rankup.dto.SectionMemberGetDto(m.id, m.firstName, m.lastName, m.jmbag, m.email, sm.active, sm.pointsAll, r.name) " +
            "FROM SectionMember sm " +
            "JOIN sm.member m " +
            "JOIN sm.rank r " +
            "WHERE sm.section.id = :idSection AND sm.member.id = :idMember")
    Optional<SectionMemberGetDto> findSectionMemberBySectionIdAndMemberId(@Param("idSection") Long idSection, @Param("idMember") Long idMember);
}
