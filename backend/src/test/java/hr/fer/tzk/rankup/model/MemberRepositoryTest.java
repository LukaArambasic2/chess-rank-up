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
    public void shouldDeclineSecondMember() {
        Member member1 = new Member("Hrvoje", "Horvat", "1234567890");
        memberRepository.save(member1);
        assertNotNull(member1.getId());

        // Should throw an error because of not unique JMBAG
        Member member2 = new Member("Ivan", "Ivanovic", "1234567890");

        Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
            memberRepository.save(member2);
        });

       assertNull(member2.getId());
    }
}
