package Clients.Models.Goal;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Schema(description = "Dto целей клиента")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateGoalDto {

    @Schema(description = "Наименование цели", example = "Накачаться")
    public String name;

    @Schema(description = "Описание цели", example = "Накачать 50-ти сантиметровые банки")
    public String description;

    @Schema(description = "Желаемая дата завершения", example = "11.12.2025")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public LocalDate desiredCompletionDate;
}