package hr.fer.tzk.rankup.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsForm {
    @NotBlank(message = "Ime obavijesti ne smije biti prazno")
    @Size(max = 30)
    private String title;

    @NotBlank(message = "Opis obavijesti ne smije biti prazan")
    @Size(max = 80)
    private String content;
    private String images;
    private Long idSection;
    private Long idMember;
}