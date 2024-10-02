package Clients.Service;

import Clients.Entity.Goal.Goal;
import Clients.Exception.NotFoundException;
import Clients.Models.Goal.GoalDTO;
import Clients.Repository.ClientRepository;
import Clients.Repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public void addGoal(Goal goal) {
        log.debug("#addGoal: goal={}", goal);
        goalRepository.save(goal);
    }

    @Transactional
    public void updateGoal(Goal goal) {
        log.debug("#updateGoal: goal={}", goal);
        goalRepository.save(goal);
    }

    public Goal findGoalById(UUID goalId) {
        log.debug("#findGoalById: goalId={}", goalId);
        return goalRepository.findById(goalId).orElseThrow(
                () -> new NotFoundException("Goal", "goalId", goalId.toString())
        );
    }

    public List<GoalDTO> getGoals(UUID clientId) {
        clientRepository.findById(clientId).orElseThrow(
                () -> new NotFoundException(String.format("Клиент с id '%s' не найден.", clientId)));

        List<Goal> goalList = goalRepository.findAllByClientId(clientId);
        List<GoalDTO> goalDTOList = new ArrayList<>();

        for (Goal goal : goalList) {
            GoalDTO goalDTO = new GoalDTO();
            goalDTO.id = goal.getId();
            goalDTO.goalName = goal.getGoalName();
            goalDTOList.add(goalDTO);
        }
        return goalDTOList;
    }

    @Transactional
    public void completeGoal(UUID goalId) {
        log.debug("#completeGoal: goalId={}", goalId);
        goalRepository.findById(goalId).ifPresentOrElse(actualGoal -> {
                    actualGoal.setCompleted(true);
                    actualGoal.setCompletionDate(LocalDate.now().atStartOfDay(ZoneOffset.UTC).toLocalDate());
                    goalRepository.save(actualGoal);
                }, () -> {
                    throw new NotFoundException(String.format("Цель с id '%s' не найдена.", goalId));
                }
        );
    }

    @Transactional
    public void doNotComplete(UUID goalId) {
        log.debug("#doNotComplete: goalId={}", goalId);
        goalRepository.findById(goalId).ifPresentOrElse(actualGoal -> {
            actualGoal.setCompleted(false);
            actualGoal.setCompletionDate(null);
            goalRepository.save(actualGoal);
        }, () -> {
            throw new NotFoundException(String.format("Цель с id '%s' не найдена.", goalId));
        });
    }

    public Goal getFullInfo(UUID goalId) {
        return findGoalById(goalId);
    }

    public void deleteGoal(UUID goalId) {
        log.debug("#deleteGoal: goalId={}", goalId);
        goalRepository.findById(goalId).ifPresentOrElse(goalRepository::delete, () -> {
            throw new NotFoundException(String.format("Цель с id '%s' не найдена.", goalId));
        });
    }
}
