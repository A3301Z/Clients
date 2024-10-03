package Clients.rest.Controller;

import Clients.Entity.Goal;
import Clients.Models.Goal.GoalMinimalDto;
import Clients.Models.Goal.GoalDto;
import Clients.Service.GoalService;
import Clients.rest.api.GoalApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GoalController implements GoalApi {

    private final GoalService goalService;

    @Override
    public void addGoal(UUID clientId, GoalDto goalMinimalDTO) {
        goalService.addGoal(clientId, goalMinimalDTO);
    }

    @Override
    public void updateGoal(GoalDto goalMinimalDTO) {
        goalService.updateGoal(goalMinimalDTO);
    }

    @Override
    public List<GoalMinimalDto> getGoals(UUID clientId) {
        return goalService.getGoals(clientId);
    }

    @Override
    public Goal getGoalFullInfo(UUID goalId) {
        return goalService.getFullInfo(goalId);
    }

    @Override
    public void completeGoal(UUID goalId) {
        goalService.completeGoal(goalId);
    }

    @Override
    public void doNotComplete(UUID goalId) {
        goalService.doNotComplete(goalId);
    }

    @Override
    public void deleteGoal(UUID goalId) {
        goalService.deleteGoal(goalId);
    }
}
