package Clients.rest.Controller;

import Clients.Models.Goal.CreateGoalDto;
import Clients.Models.Goal.GoalDto;
import Clients.Models.Goal.GoalMinimalDto;
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
    public void addGoal(UUID clientId, CreateGoalDto createGoalDto) {
        goalService.addGoal(clientId, createGoalDto);
    }

    @Override
    public void updateGoal(UUID clientId, GoalDto goalDto) {
        goalService.updateGoal(clientId, goalDto);
    }

    @Override
    public List<GoalMinimalDto> getGoals(UUID clientId) {
        return goalService.getGoals(clientId);
    }

    @Override
    public GoalDto getGoalFullInfo(UUID goalId) {
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
