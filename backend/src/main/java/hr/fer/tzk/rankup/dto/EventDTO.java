package hr.fer.tzk.rankup.dto;

import hr.fer.tzk.rankup.model.Section;

import java.time.LocalDate;

public class EventDTO {
    private String name;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String description;
    private Long idSection;
    private Long idEventType;

    public EventDTO() {
    }

    public EventDTO(String name, LocalDate dateFrom, LocalDate dateTo, String description, Long idSection, Long idEventType) {
        this.name = name;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
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

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
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
