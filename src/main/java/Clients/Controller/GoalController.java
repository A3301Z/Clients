package Clients.Controller;

import Clients.Entity.Client.Client;
import Clients.Entity.Goal.Goal;
import Clients.Models.Goal.GoalDTO;
import Clients.Models.Goal.GoalMinimalDTO;
import Clients.Models.Views.Public;
import Clients.Repository.ClientRepository;
import Clients.Service.GoalService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class GoalController {
    private final GoalService service;
    private final ClientRepository clientRepository;

    public GoalController(@Autowired GoalService service, @Autowired ClientRepository clientRepository) {
        this.service          = service;
        this.clientRepository = clientRepository;
    }

    @Tag(name = "ЦЕЛЬ: Добавить в список.")
    @PostMapping("/client/{clientId}/newGoal")
    public void addGoals(@PathVariable UUID clientId, @RequestBody @JsonView(Public.class) GoalMinimalDTO goalMinimalDTO) {

        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            throw new RuntimeException(String.format("Цель не добавлена. Клиент с id '%s' не найден.", clientId));
        } else {
            Goal goal = new Goal();
            goal.setId(UUID.randomUUID());
            goal.setClient(client.get());
            goal.setGoalName(goalMinimalDTO.goalName);
            goal.setGoalDescription(goalMinimalDTO.goalDescription);
            goal.setDesiredCompletionDate(goalMinimalDTO.desiredCompletionDate);
            goal.setCompleted(goalMinimalDTO.isCompleted);
            goal.setCompletionDate(goalMinimalDTO.completionDate);

            service.addGoal(goal);
        }
    }

    @Tag(name = "ЦЕЛЬ: Обновить.")
    @PutMapping("/client/goal")
    public void updateGoal(@RequestBody GoalMinimalDTO goalMinimalDTO) {

        Goal goal = service.findGoalById(goalMinimalDTO.id);
        goal.setGoalName(goalMinimalDTO.goalName);
        goal.setGoalDescription(goalMinimalDTO.goalDescription);
        goal.setDesiredCompletionDate(goalMinimalDTO.desiredCompletionDate);
        goal.setCompleted(goalMinimalDTO.isCompleted);
        goal.setCompletionDate(goalMinimalDTO.completionDate);

        service.updateGoal(goal);
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
