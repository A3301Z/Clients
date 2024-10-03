package Clients.Models.Goal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto минимальная информация о цели клиента")
public class GoalMinimalDto {

    @Schema(description = "Уникальный идентификатор цели")
    public UUID id;

    @Schema(description = "Наименование цели")
    public String name;
}
