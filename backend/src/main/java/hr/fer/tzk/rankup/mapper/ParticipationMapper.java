package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.ProfileEventDto;
import hr.fer.tzk.rankup.model.Participation;

public class ParticipationMapper {

    public static ProfileEventDto toDto(Participation participation) {
        if (participation == null) {
            return null;
        }
        int points = participation.getAddPoints() + participation.getEvent().getEventType().getDefaultPoints();

        ProfileEventDto dto = new ProfileEventDto();
        dto.setDate(participation.getEvent().getDate());
        dto.setName(participation.getEvent().getName());
        dto.setPoints(points);

        return dto;
    }
}
