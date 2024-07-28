package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.repository.MemberRepository;
import hr.fer.tzk.rankup.repository.SectionMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MemberRepository memberRepository, SectionMemberRepository sectionMemberRepository) {
        this.memberRepository = memberRepository;
        this.sectionMemberRepository = sectionMemberRepository;
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findMemberByJmbag(String jmbag) {
        return memberRepository.findByJmbag(jmbag);
    }

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    public void deleteMemberByJmbag(String jmbag) {
        memberRepository.deleteByJmbag(jmbag);
    }

    public void deleteMemberByEmail(String email) {
        memberRepository.deleteByEmail(email);
    }
}
