package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.ScoreboardDto;
import hr.fer.tzk.rankup.mapper.SectionMemberMapper;
import hr.fer.tzk.rankup.mapper.SectionSemesterMapper;
import hr.fer.tzk.rankup.model.SectionMember;
import hr.fer.tzk.rankup.model.SectionSemester;
import hr.fer.tzk.rankup.model.Semester;
import hr.fer.tzk.rankup.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ScoreboardService {

    private final SectionMemberService sectionMemberService;
    private final SemesterService semesterService;
    private final SectionSemesterService sectionSemesterService;
    private final SemesterRepository semesterRepository;

    @Autowired
    public ScoreboardService(SectionMemberService sectionMemberService, SemesterService semesterService, SectionSemesterService sectionSemesterService, SemesterRepository semesterRepository) {
        this.sectionMemberService = sectionMemberService;
        this.semesterService = semesterService;
        this.sectionSemesterService = sectionSemesterService;
        this.semesterRepository = semesterRepository;
    }

    public List<ScoreboardDto> getPointsTotal(Long idSection) {
        List<SectionMember> members = sectionMemberService.findAllSectionMembersByIdSection(idSection);

        return members.stream()
                .map(SectionMemberMapper::toScoreboardDto)
                .sorted(Comparator.comparing(ScoreboardDto::getPoints).reversed())
                .toList();
    }

    // TODO: Fix this.
    public List<ScoreboardDto> getPointsYear(Long idSection) {
        int currentYear = LocalDate.now().getYear();
        List<Semester> semesters = findSemestersForYear(currentYear);

        if (semesters.isEmpty()) {
            int previousYear = currentYear - 1;
            semesters = findSemestersForYear(previousYear);
        }

        if (semesters.isEmpty()) {
            return List.of();
        }

        // Combine the points from the found semesters
        List<SectionSemester> sectionSemesters = semesters.stream()
                .flatMap(semester -> sectionSemesterService.findSectionSemesterByIdSectionAndIdSemester(idSection, semester.getId()).stream())
                .toList();

        return sectionSemesters.stream()
                .map(SectionSemesterMapper::toScoreboardDto)
                .sorted(Comparator.comparing(ScoreboardDto::getPoints).reversed())
                .toList();
    }

    private List<Semester> findSemestersForYear(int year) {
        String yearPattern = String.valueOf(year - 2000);
        return semesterRepository.findSemestersBySpecificPatterns(yearPattern);
    }

    public List<ScoreboardDto> getPointsSemester(Long idSection) {
        Optional<Semester> latestSemesterOpt = semesterService.findLatestSemester();
        if (latestSemesterOpt.isEmpty()) {
            return List.of();
        }

        Semester latestSemester = latestSemesterOpt.get();
        List<SectionSemester> pointsCurrentSemester = sectionSemesterService.findSectionSemesterByIdSectionAndIdSemester(idSection, latestSemester.getId());
        return pointsCurrentSemester.stream()
                .map(SectionSemesterMapper::toScoreboardDto)
                .sorted(Comparator.comparing(ScoreboardDto::getPoints).reversed())
                .toList();
    }
}
