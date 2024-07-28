package hr.fer.tzk.rankup.dto;

public class EventTypeDTO {
    private String name;
    private Integer defaultPoints;

    public EventTypeDTO() {
    }

    public EventTypeDTO(String name, Integer defaultPoints) {
        this.name = name;
        this.defaultPoints = defaultPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefaultPoints() {
        return defaultPoints;
    }

    public void setDefaultPoints(Integer defaultPoints) {
        this.defaultPoints = defaultPoints;
    }
}
