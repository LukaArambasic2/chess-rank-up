package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findVerificationByUrl(String url);
}
