package Clients.rest.api;

import Clients.Entity.Goal;
import Clients.Models.Goal.GoalMinimalDto;
import Clients.Models.Goal.GoalDto;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "API целей клиентов")
@RequestMapping("/api/v3/goals")
public interface GoalApi {
    @Operation(summary = "Добавить в список")
    @PostMapping("/clients/{clientId}")
    void addGoal(
            @PathVariable UUID clientId, @RequestBody @JsonView GoalDto goalMinimalDTO);

    @Operation(summary = "Обновить")
    @PutMapping("/clients/goal")
    void updateGoal(@RequestBody GoalDto goalMinimalDTO);

    @Operation(summary = "Получить весь список")
    @GetMapping("/clients/{clientId}/goals")
    List<GoalMinimalDto> getGoals(@PathVariable UUID clientId);

    @Operation(summary = "Полная информация о цели")
    @GetMapping("/clients/goal/{goalId}/fullInfo")
    Goal getGoalFullInfo(@PathVariable UUID goalId);

    @Operation(summary = "Завершить")
    @PutMapping("/clients/goal/{goalId}/complete")
    void completeGoal(@PathVariable UUID goalId);

    @Operation(summary = "Сделать не завершенной")
    @PutMapping("/clients/goal/{goalId}/doNotComplete")
    void doNotComplete(@PathVariable UUID goalId);

    @Operation(summary = "Удалить")
    @DeleteMapping("/clients/goal/{goalId}/delete")
    void deleteGoal(@PathVariable UUID goalId);
}
