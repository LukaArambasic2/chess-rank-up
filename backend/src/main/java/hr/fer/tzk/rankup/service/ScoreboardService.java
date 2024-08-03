package hr.fer.tzk.rankup.service;

import hr.fer.tzk.rankup.dto.ScoreboardDto;
import hr.fer.tzk.rankup.mapper.SectionMemberMapper;
import hr.fer.tzk.rankup.mapper.SectionSemesterMapper;
import hr.fer.tzk.rankup.model.SectionMember;
import hr.fer.tzk.rankup.model.SectionSemester;
import hr.fer.tzk.rankup.model.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreboardService {

    private final SectionMemberService sectionMemberService;
    private final SemesterService semesterService;
    private final SectionSemesterService sectionSemesterService;

    @Autowired
    public ScoreboardService(SectionMemberService sectionMemberService, SemesterService semesterService, SectionSemesterService sectionSemesterService) {
        this.sectionMemberService = sectionMemberService;
        this.semesterService = semesterService;
        this.sectionSemesterService = sectionSemesterService;
    }

    public List<ScoreboardDto> getPointsTotal(Long idSection) {
        List<SectionMember> members = sectionMemberService.findAllSectionMembersByIdSection(idSection);
        return members.stream()
                .map(SectionMemberMapper::toScoreboardDto)
                .sorted(Comparator.comparing(ScoreboardDto::getPoints).reversed())
                .toList();
    }

    public List<ScoreboardDto> getPointsYear(Long idSection) {
        Optional<Semester> latestOpt = semesterService.findLatestSemester();
        if (latestOpt.isEmpty()) {
            return List.of();
        }

        Semester latest = latestOpt.get();
        String name = latest.getName();

        // There is only Winter semester, so use only that.
        if (!name.endsWith("LJS")) {
            return getPointsSemester(idSection);
        }

        Map<Long, ScoreboardDto> scoreboardWithMemberId = new HashMap<>();
        List<Semester> lastTwo = semesterService.findLatestNSemesters(2);
        for (Semester sem : lastTwo) {
             List<SectionSemester> pointsCurrentSemester = sectionSemesterService.findSectionSemesterByIdSectionAndIdSemester(idSection, sem.getId());
            for (SectionSemester ss : pointsCurrentSemester) {
                Long idMember = ss.getMember().getId();
                ScoreboardDto scoreboard = scoreboardWithMemberId.get(idMember);
                if (scoreboard == null) {
                    scoreboard = SectionSemesterMapper.toScoreboardDto(ss);
                    scoreboardWithMemberId.put(idMember, scoreboard);
                } else {
                    scoreboard.setPoints(scoreboard.getPoints() + ss.getPoints());
                }
            }
        }

        return scoreboardWithMemberId.values()
                .stream()
                .sorted(Comparator.comparing(ScoreboardDto::getPoints).reversed())
                .toList();
    }

    public List<ScoreboardDto> getPointsSemester(Long idSection) {
        Optional<Semester> latestOpt = semesterService.findLatestSemester();
        if (latestOpt.isEmpty()) {
            return List.of();
        }

        Semester latest = latestOpt.get();
        List<SectionSemester> pointsCurrentSemester = sectionSemesterService.findSectionSemesterByIdSectionAndIdSemester(idSection, latest.getId());
        return pointsCurrentSemester.stream()
                .map(SectionSemesterMapper::toScoreboardDto)
                .sorted(Comparator.comparing(ScoreboardDto::getPoints).reversed())
                .toList();
    }
}
