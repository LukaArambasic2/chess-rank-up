package hr.fer.tzk.rankup.config;

import hr.fer.tzk.rankup.security.Argon2idHasher;
import hr.fer.tzk.rankup.security.BCryptHasher;
import hr.fer.tzk.rankup.security.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PasswordHasher argon2idHasher() {
        return new Argon2idHasher();
    }

    @Bean
    public PasswordHasher bcryptHasher() {
        return new BCryptHasher();
    }
}
