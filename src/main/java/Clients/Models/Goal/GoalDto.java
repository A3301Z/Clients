package Clients.Models.Goal;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Dto целей клиента")
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GoalDto {

    @Schema(description = "Уникальный идентификатор")
    public UUID id;

    @Schema(description = "Наименование цели", example = "Накачаться")
    public String name;

    @Schema(description = "Описание цели", example = "Накачать 50-ти сантиметровые банки")
    public String description;

    @Schema(description = "Желаемая дата завершения", example = "11.12.2025")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public LocalDate desiredCompletionDate;

    @Schema(description = "Флаг достижения цели", example = "false")
    public boolean isCompleted;

    @Schema(description = "Дата достижения цели", example = "11.12.2025")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public LocalDate completionDate;
}
