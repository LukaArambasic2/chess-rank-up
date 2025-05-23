package hr.fer.tzk.rankup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import hr.fer.tzk.rankup.model.Member;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByJmbag(String jmbag);

    Optional<Member> findByEmail(String email);

    void deleteByJmbag(String jmbag);

    void deleteByEmail(String email);

}
