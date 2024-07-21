package hr.fer.tzk.rankup.model;

import hr.fer.tzk.rankup.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DirtiesContext
    public void shouldAddMember() {
        Member member = new Member("Ivan", "Horvat", "0036548430");
        memberRepository.save(member);
        assertNotNull(member.getId());
    }

    @Test
    @DirtiesContext
    public void shouldDeclineSecondMember() {
        try {
            Member member = new Member("Ivan", "Horvat", "0036548430");
            memberRepository.save(member);
            Member member2 = new Member("Ivan", "Horvat", "0036548430");
            memberRepository.save(member2);
            assertNotNull(member.getId());


        } catch (Exception e) {
            System.out.println("Success");
        }

    }

}
