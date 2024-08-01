package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.model.SectionSemester;
import hr.fer.tzk.rankup.repository.SectionSemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionSemesterService {

    private final SectionSemesterRepository sectionSemesterRepository;

    @Autowired
    public SectionSemesterService(SectionSemesterRepository sectionSemesterRepository) {
        this.sectionSemesterRepository = sectionSemesterRepository;
    }

    public Optional<SectionSemester> findSectionSemesterByAlterKey(Long idMember, Long idSection, Long idSemester) {
        return sectionSemesterRepository.findByMember_IdAndSection_IdAndSemester_Id(idMember, idSection, idSemester);
    }

    public List<SectionSemester> findSectionSemesterByIdSectionAndIdSemester(Long idSection, Long idSemester) {
        return sectionSemesterRepository.findBySection_IdAndSemester_Id(idSection, idSemester);
    }
}
