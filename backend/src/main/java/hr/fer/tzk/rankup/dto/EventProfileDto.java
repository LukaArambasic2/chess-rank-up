package hr.fer.tzk.rankup.dto;

import java.time.LocalDate;
import java.util.Objects;

public class EventProfileDto {
    private LocalDate date;
    private String name;
    private int points;

    public EventProfileDto() { }

    public EventProfileDto(LocalDate date, String name, int points) {
        this.date = date;
        this.name = name;
        this.points = points;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventProfileDto that = (EventProfileDto) o;
        return points == that.points && Objects.equals(date, that.date) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, name, points);
    }
}
