package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.Member;
import hr.fer.tzk.rankup.model.Rank;
import hr.fer.tzk.rankup.model.Section;
import hr.fer.tzk.rankup.model.SectionMember;
import hr.fer.tzk.rankup.repository.SectionMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionMemberService {

    private final SectionMemberRepository sectionMemberRepository;
    private final MemberService memberService;
    private final SectionService sectionService;
    private final RankService rankService;

    @Autowired
    public SectionMemberService(SectionMemberRepository sectionMemberRepository, MemberService memberService, SectionService sectionService, RankService rankService) {
        this.sectionMemberRepository = sectionMemberRepository;
        this.memberService = memberService;
        this.sectionService = sectionService;
        this.rankService = rankService;
    }

    public List<SectionMember> findAllSectionMembersByIdSection(Long idSection) {
        return sectionMemberRepository.findAllBySection_Id(idSection);
    }

    public Optional<SectionMember> findSectionMemberByIdSection(Long idMember, Long idSection) {
        return sectionMemberRepository.findSectionMemberByMember_IdAndSection_Id(idMember, idSection);
    }

    public Optional<SectionMember> createSectionMemberFromJmbagAndRank(Long idSection, String jmbag, String rankName) {
        Optional<Member> memberOpt = memberService.findMemberByJmbag(jmbag);
        Optional<Section> sectionOpt = sectionService.findSectionById(idSection);
        Optional<Rank> rankOpt = rankService.findRankByName(rankName);
        if (memberOpt.isEmpty() || sectionOpt.isEmpty() || rankOpt.isEmpty()) {
            return Optional.empty();
        }
        SectionMember member = new SectionMember();
        member.setMember(memberOpt.get());
        member.setSection(sectionOpt.get());
        member.setRank(rankOpt.get());
        return Optional.of(sectionMemberRepository.save(member));
    }
}
