package hr.fer.tzk.rankup.mapper;

import hr.fer.tzk.rankup.dto.BasicMemberDto;
import hr.fer.tzk.rankup.dto.DetailedMemberDto;
import hr.fer.tzk.rankup.form.BasicMemberForm;
import hr.fer.tzk.rankup.model.Member;

public class MemberMapper {

    public static DetailedMemberDto toDetailedDto(Member member) {
        if (member == null) {
            return null;
        }

        DetailedMemberDto dto = new DetailedMemberDto();
        dto.setId(member.getId());
        dto.setFirstName(member.getFirstName());
        dto.setLastName(member.getLastName());
        dto.setJmbag(member.getJmbag());
        dto.setEmail(member.getEmail());

        return dto;
    }

    public static BasicMemberDto toBasicDto(Member member) {
        if (member == null) {
            return null;
        }

        BasicMemberDto dto = new BasicMemberDto();
        dto.setFirstName(member.getFirstName());
        dto.setLastName(member.getLastName());
        dto.setJmbag(member.getJmbag());

        return dto;
    }

    public static BasicMemberDto fromFormToDto(BasicMemberForm form) {
        if (form == null) {
            return null;
        }

        BasicMemberDto dto = new BasicMemberDto();
        dto.setFirstName(form.getFirstName());
        dto.setLastName(form.getLastName());
        dto.setJmbag(form.getJmbag());

        return dto;
    }
}
