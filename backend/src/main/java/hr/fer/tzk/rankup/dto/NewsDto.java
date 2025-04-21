package hr.fer.tzk.rankup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private Long id;
    private String title;
    private LocalDate dateCreated;
    private LocalDate dateEdited;
    private String content;
    private String images;
    private BasicMemberDto author;
}
