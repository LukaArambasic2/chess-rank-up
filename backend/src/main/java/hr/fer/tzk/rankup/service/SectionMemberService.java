package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.SectionMemberGetDto;
import hr.fer.tzk.rankup.repository.SectionMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionMemberService {

    private SectionMemberRepository sectionMemberRepository;

    @Autowired
    public SectionMemberService(SectionMemberRepository sectionMemberRepository) {
        this.sectionMemberRepository = sectionMemberRepository;
    }

    public List<SectionMemberGetDto> findAllSectionMembersBySectionId(Long idSection) {
        return sectionMemberRepository.findAllSectionMembersBySectionId(idSection);
    }

    public Optional<SectionMemberGetDto> findSectionMemberBySectionId(Long idSection, Long idMember) {
        return sectionMemberRepository.findSectionMemberBySectionIdAndMemberId(idSection, idMember);
    }
}
