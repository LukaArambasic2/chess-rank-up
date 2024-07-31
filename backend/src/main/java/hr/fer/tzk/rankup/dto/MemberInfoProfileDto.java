package hr.fer.tzk.rankup.dto;

import hr.fer.tzk.rankup.model.MemberInfo;

import java.util.Objects;

public class MemberInfoProfileDto {
    private String name;
    // TODO: Use generic
    private String value;

    public MemberInfoProfileDto() { }

    public MemberInfoProfileDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberInfoProfileDto that = (MemberInfoProfileDto) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    @Override
    public String toString() {
        return "MemberInfoProfileDto{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
