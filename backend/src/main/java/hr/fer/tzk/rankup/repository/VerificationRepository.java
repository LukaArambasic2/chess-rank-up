package hr.fer.tzk.rankup.repository;

import hr.fer.tzk.rankup.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
    Optional<Verification> findVerificationByUrl(String url);
}
