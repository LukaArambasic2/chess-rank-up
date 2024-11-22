package hr.fer.tzk.rankup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardDto {
    private String firstName;
    private String lastName;
    private Number memberInfo;

    public int getMemberInfoAsInt() {
        if (memberInfo instanceof Integer) {
            return (Integer) memberInfo;
        } else if (memberInfo instanceof Float) {
            return ((Float) memberInfo).intValue();
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }

    public float getMemberInfoAsFloat() {
        if (memberInfo instanceof Integer) {
            return ((Integer) memberInfo).floatValue();
        } else if (memberInfo instanceof Float) {
            return (Float) memberInfo;
        } else {
            throw new IllegalArgumentException("Unsupported number type");
        }
    }
}
