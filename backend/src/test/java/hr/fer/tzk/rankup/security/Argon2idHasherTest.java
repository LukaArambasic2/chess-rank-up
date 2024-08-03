package hr.fer.tzk.rankup.security;

import hr.fer.tzk.rankup.form.LoginForm;
import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
@ActiveProfiles("test")
public class Argon2idHasherTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    @Qualifier("argon2idHasher")
    PasswordHasher argon2idHasher;

    @Test
    void testMemberPasswordVerification() {
        LoginForm loginInfo1 = new LoginForm("jj56789@fer.hr", "password1");
        LoginForm loginInfo2 = new LoginForm("pp53838@fer.hr", "password2");
        LoginForm loginInfo3 = new LoginForm("jj53890@pmf.hr", "password3");

        // Verify password for each member
        verifyMemberPassword(loginInfo1);
        verifyMemberPassword(loginInfo2);
        verifyMemberPassword(loginInfo3);
    }

    void verifyMemberPassword(LoginForm loginInfo) {
        String email = loginInfo.getEmail();
        String password = loginInfo.getPassword();
        Optional<Member> storedMemberOpt = memberRepository.findByEmail(email);
        assertThat(storedMemberOpt).isPresent();

        Member storedMember = storedMemberOpt.get();
        String salt = storedMember.getSalt();
        String storedHash = storedMember.getPasswordHash();

        boolean isPasswordCorrect = argon2idHasher.checkPassword(password, salt, storedHash);
        assertThat(isPasswordCorrect).isTrue();
    }
}
