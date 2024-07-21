package hr.fer.tzk.rankup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import hr.fer.tzk.rankup.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
