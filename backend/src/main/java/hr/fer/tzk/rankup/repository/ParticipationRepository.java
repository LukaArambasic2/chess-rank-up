package hr.fer.tzk.rankup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.tzk.rankup.model.Participation;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    List<Participation> findByIdMember(Long idMember);

    List<Participation> findByIdEvent(Long idEvent);

    Optional<Participation> findByIdMemberAndIdEvent(Long idMember, Long idEvent);

}
