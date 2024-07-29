package hr.fer.tzk.rankup.dto;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SectionMemberProfileDto {
    private String firstName;
    private String lastName;
    private String jmbag;
    private String rank;
    private List<MemberInfoProfileDto> memberInfos;
    private List<EventProfileDto> events;

    public SectionMemberProfileDto() { }

    public SectionMemberProfileDto(String firstName, String lastName, String jmbag, String rank, List<MemberInfoProfileDto> memberInfos, List<EventProfileDto> events) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jmbag = jmbag;
        this.rank = rank;
        this.memberInfos = memberInfos;
        this.events = events;
    }

    public void addMemberInfo(MemberInfoProfileDto memberInfo) {
        memberInfos.add(memberInfo);
    }

    public void addEvent(EventProfileDto event) {
        events.add(event);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<MemberInfoProfileDto> getMemberInfos() {
        return memberInfos;
    }

    public void setMemberInfos(List<MemberInfoProfileDto> memberInfos) {
        this.memberInfos = memberInfos;
    }

    public List<EventProfileDto> getEvents() {
        return events;
    }

    public void setEvents(List<EventProfileDto> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionMemberProfileDto that = (SectionMemberProfileDto) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(jmbag, that.jmbag) && Objects.equals(rank, that.rank) && Objects.equals(memberInfos, that.memberInfos) && Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, jmbag, rank, memberInfos, events);
    }

    @Override
    public String toString() {
        return "SectionMemberProfileDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", jmbag='" + jmbag + '\'' +
                ", rank='" + rank + '\'' +
                ", memberInfos=" + memberInfos +
                ", events=" + events +
                '}';
    }
}
