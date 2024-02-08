package Clients.Controller;

import Clients.Entity.Goal.Goal;
import Clients.Models.Goal.GoalDTO;
import Clients.Models.Goal.GoalMinimalDTO;
import Clients.Service.GoalService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GoalController {
    private final GoalService service;

    public GoalController(@Autowired GoalService service) {
        this.service = service;
    }

    @Tag(name = "ЦЕЛЬ: Добавить в список.")
    @PostMapping("/client/{id}/newGoal")
    public void addGoals(@PathVariable UUID id, @RequestBody GoalMinimalDTO goalMinimalDTO) {
        service.addGoal(id, goalMinimalDTO);
    }

    @Tag(name = "ЦЕЛЬ: Обновить.")
    @PutMapping("/client/goal/{goalId}/update")
    public void updateGoal(@PathVariable UUID goalId, @RequestBody GoalMinimalDTO goalMinimalDTO) {
        service.updateGoal(goalId, goalMinimalDTO);
    }

    @Tag(name = "ЦЕЛЬ: Получить весь список.")
    @GetMapping("/client/{clientId}/goals")
    public List<GoalDTO> getGoals(@PathVariable UUID clientId) {
        return service.getGoals(clientId);
    }

    @Tag(name = "ЦЕЛЬ: Полная информация о цели.")
    @GetMapping("/client/goal/{goalId}/fullInfo")
    public Goal getGoalFullInfo(@PathVariable UUID goalId) {
        return service.getFullInfo(goalId);
    }

    @Tag(name = "ЦЕЛЬ: Завершить.")
    @PutMapping("/client/goal/{goalId}/complete")
    public void completeGoal(@PathVariable UUID goalId) {
        service.completeGoal(goalId);
    }

    @Tag(name = "ЦЕЛЬ: Сделать не завершенной.")
    @PutMapping("/client/goal/{goalId}/doNotComplete")
    public void doNotComplete(@PathVariable UUID goalId) {
        service.doNotComplete(goalId);
    }

    @Tag(name = "ЦЕЛЬ: Удалить.")
    @DeleteMapping("/client/goal/{goalId}/delete")
    public void deleteGoal(@PathVariable UUID goalId) {
        service.deleteGoal(goalId);
    }
}
