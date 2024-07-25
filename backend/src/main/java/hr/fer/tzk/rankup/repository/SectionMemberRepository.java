package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.model.SectionMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionMemberRepository extends JpaRepository<Section, Long> {

    //Optional<SectionMember> findBySectionIdAndMemberId(Long sectionId, Long memberId);

    //List<SectionMember> findAllBySectionId(Long idSection);
}
