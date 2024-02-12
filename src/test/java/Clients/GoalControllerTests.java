package Clients;

import Clients.Controller.GoalController;
import Clients.Entity.Goal.Goal;
import Clients.Models.Goal.GoalDTO;
import Clients.Models.Goal.GoalMinimalDTO;
import Clients.Service.GoalService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class GoalControllerTests {

    @Mock
    private GoalService goalService;

    @InjectMocks
    private GoalController goalController;

    @Test
    void testAddGoals() {

    }

    @Test
    void testUpdateGoal() {

    }

    @Test
    void testGetGoals() {
        UUID clientId = UUID.randomUUID();
        List<GoalDTO> expectedGoals = List.of(new GoalDTO(), new GoalDTO());
        when(goalService.getGoals(clientId)).thenReturn(expectedGoals);
        List<GoalDTO> actualGoals = goalController.getGoals(clientId);
        assertEquals(expectedGoals.size(), actualGoals.size());
    }

    @Test
    void testGetGoalFullInfo() {
        UUID goalId = UUID.randomUUID();
        Goal expectedGoal = new Goal();
        when(goalService.getFullInfo(goalId)).thenReturn(expectedGoal);
        Goal actualGoal = goalController.getGoalFullInfo(goalId);
        assertEquals(expectedGoal, actualGoal);
    }

    @Test
    void testCompleteGoal() {
        UUID goalId = UUID.randomUUID();
        goalController.completeGoal(goalId);
        verify(goalService, times(1)).completeGoal(goalId);
    }

    @Test
    void testDoNotComplete() {
        UUID goalId = UUID.randomUUID();
        goalController.doNotComplete(goalId);
        verify(goalService, times(1)).doNotComplete(goalId);
    }

    @Test
    void testDeleteGoal() {
        UUID goalId = UUID.randomUUID();
        goalController.deleteGoal(goalId);
        verify(goalService, times(1)).deleteGoal(goalId);
    }
}
