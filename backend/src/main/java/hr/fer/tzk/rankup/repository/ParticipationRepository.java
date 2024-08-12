package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.tzk.rankup.model.Participation;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    List<Participation> findAllByMember_Id(Long idMember);
}