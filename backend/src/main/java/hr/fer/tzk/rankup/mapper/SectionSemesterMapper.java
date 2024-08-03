package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.ScoreboardDto;
import hr.fer.tzk.rankup.model.SectionSemester;

public class SectionSemesterMapper {

    public static ScoreboardDto toScoreboardDto(SectionSemester sectionSemester) {
        if (sectionSemester == null) {
            return null;
        }

        ScoreboardDto scoreboardDto = new ScoreboardDto();
        scoreboardDto.setFirstName(sectionSemester.getMember().getFirstName());
        scoreboardDto.setLastName(sectionSemester.getMember().getLastName());
        scoreboardDto.setPoints(sectionSemester.getPoints());

        return scoreboardDto;
    }
}
