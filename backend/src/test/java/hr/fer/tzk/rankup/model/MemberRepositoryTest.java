package hr.fer.tzk.rankup.model;

import hr.fer.tzk.rankup.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DirtiesContext
    public void shouldAddMember() {
        Member member = new Member("Hrvoje", "Horvat", "1234567890");
        memberRepository.save(member);
        assertNotNull(member.getId());
    }

    @Test
    @DirtiesContext
    public void shouldGiveErrorBecauseDuplicates() {
        Member member1 = new Member("Hrvoje", "Horvat", "1234567890", "hh56789@fer.hr");
        memberRepository.save(member1);
        assertNotNull(member1.getId());

        // Should throw an error because of the duplicate JMBAG
        Member member2 = new Member("Ivan", "Ivanovic", "1234567890");
        Exception exception1 = assertThrows(DataIntegrityViolationException.class, () -> {
            memberRepository.save(member2);
        });
       assertNull(member2.getId());

        // Should throw an error because of the duplicate email
        Member member3 = new Member("Ivan", "Ivanovic", "6234567890", "hh56789@fer.hr");
        Exception exception2 = assertThrows(DataIntegrityViolationException.class, () -> {
            memberRepository.save(member3);
        });
        assertNull(member3.getId());
    }

    @Test
    @DirtiesContext
    public void shouldDeclineBecauseInvalidJmbag() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            Member member1 = new Member("Hrvoje", "Horvat", "123456789");
        });

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            Member member2 = new Member("Hrvoje", "Horvat", "12345678900");
        });

        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            Member member3 = new Member("Hrvoje", "Horvat", "1234567891");
        });
    }
}
