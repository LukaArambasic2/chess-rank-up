package hr.fer.tzk.rankup.dto;

import java.time.LocalDate;

public class EventDTO {
    private String name;
    private LocalDate date;
    private String description;
    private Long idSection;
    private Long idEventType;

    public EventDTO() {
    }

    public EventDTO(String name, LocalDate date, String description, Long idSection, Long idEventType) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.idSection = idSection;
        this.idEventType = idEventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDateFrom(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdSection() {
        return idSection;
    }

    public void setIdSection(Long idSection) {
        this.idSection = idSection;
    }

    public Long getIdEventType() {
        return idEventType;
    }

    public void setIdEventType(Long idEventType) {
        this.idEventType = idEventType;
    }
}
