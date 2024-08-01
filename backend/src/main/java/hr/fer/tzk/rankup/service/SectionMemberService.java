package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.SectionMember;
import hr.fer.tzk.rankup.repository.SectionMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionMemberService {

    private final SectionMemberRepository sectionMemberRepository;

    @Autowired
    public SectionMemberService(SectionMemberRepository sectionMemberRepository) {
        this.sectionMemberRepository = sectionMemberRepository;
    }

    public List<SectionMember> findAllSectionMembersByIdSection(Long idSection) {
        return sectionMemberRepository.findAllBySection_Id(idSection);
    }

    public Optional<SectionMember> findSectionMemberByIdSection(Long idMember, Long idSection) {
        return sectionMemberRepository.findSectionMemberByMember_IdAndSection_Id(idMember, idSection);
    }
}
