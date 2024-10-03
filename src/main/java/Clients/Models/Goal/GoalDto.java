package Clients.Models.Goal;

import Clients.Entity.Goal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Schema(description = "Dto целей клиента")
@Builder(builderMethodName = "goalMinBuilder")
@AllArgsConstructor
@RequiredArgsConstructor
public class GoalDto {

    @Schema(description = "Уникальный идентификатор")
    public UUID id;

    @Schema(description = "Наименование цели")
    public String name;

    @Schema(description = "Описание цели")
    public String description;

    @Schema(description = "Желаемая дата завершения")
    public LocalDate desiredCompletionDate;

    @Schema(description = "Флаг достижения цели")
    public boolean isCompleted;

    @Schema(description = "Дата достижения цели")
    public LocalDate completionDate;



    public static Goal goalDtoToGoal(GoalDto dto, Goal goal) {
        return goal.toBuilder()
                .name(dto.name)
                .description(dto.description)
                .desiredCompletionDate(dto.desiredCompletionDate)
                .isCompleted(dto.isCompleted)
                .completionDate(dto.completionDate)
                .build();
    }
}
