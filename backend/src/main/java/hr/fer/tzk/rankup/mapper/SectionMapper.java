package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.SectionDto;
import hr.fer.tzk.rankup.form.SectionForm;
import hr.fer.tzk.rankup.model.Section;

public class SectionMapper {

    public static SectionDto toDto(Section section) {
        if (section == null) {
            return null;
        }

        SectionDto dto = new SectionDto();
        dto.setId(section.getId());
        dto.setName(section.getName());
        dto.setDescriptionUrl(section.getDescription());
        dto.setLogoUrl(section.getLogo());

        return dto;
    }

    public static Section fromForm(SectionForm form) {
        if (form == null) {
            return null;
        }

        Section section = new Section();
        section.setName(form.getName());
        section.setDescription(form.getDescription());
        section.setLogo(form.getLogo());

        return section;
    }
}
