package Clients;

import Clients.Entity.Goal;
import Clients.Models.Goal.GoalMinimalDto;
import Clients.Service.GoalService;
import Clients.rest.Controller.GoalController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GoalControllerTests {

    @Mock
    private GoalService goalService;
    @InjectMocks
    private GoalController goalController;

    @Test
    void testGetGoals() {
        UUID clientId = UUID.randomUUID();
        List<GoalMinimalDto> expectedGoals = List.of(new GoalMinimalDto(), new GoalMinimalDto());
        when(goalService.getGoals(clientId)).thenReturn(expectedGoals);
        List<GoalMinimalDto> actualGoals = goalController.getGoals(clientId);
        assertEquals(expectedGoals.size(), actualGoals.size());
    }

//    @Test
//    void testGetGoalFullInfo() {
//        UUID goalId = UUID.randomUUID();
//        Goal expectedGoal = new Goal();
//        when(goalService.getFullInfo(goalId)).thenReturn(expectedGoal);
//        Goal actualGoal = goalController.getGoalFullInfo(goalId);
//        assertEquals(expectedGoal, actualGoal);
//    }

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
