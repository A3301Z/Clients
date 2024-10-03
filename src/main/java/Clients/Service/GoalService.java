package Clients.Service;

import Clients.Entity.Goal;
import Clients.Exception.NotFoundException;
import Clients.Models.Goal.GoalMinimalDto;
import Clients.Models.Goal.GoalDto;
import Clients.Repository.ClientRepository;
import Clients.Repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static Clients.Models.Goal.GoalDto.goalDtoToGoal;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public void addGoal(UUID clientID, GoalDto goalMinimalDTO) {
        log.debug("#addGoal: clientId = {} goalMinimalDto = {}", clientID, goalMinimalDTO);

        clientRepository.findById(clientID).ifPresent(client -> {
            goalRepository.save(
                    Goal.builder()
                            .name(goalMinimalDTO.name)
                            .description(goalMinimalDTO.description)
                            .build()
            );
        });
    }

    @Transactional
    public void updateGoal(GoalDto goalMinimalDTO) {
        log.debug("#updateGoal: goalMinimalDTO={}", goalMinimalDTO);
        goalRepository.findById(goalMinimalDTO.id).ifPresentOrElse(goal -> {
            goalDtoToGoal(goalMinimalDTO, goal);
            goalRepository.save(goal);
        }, () -> {
            throw new NotFoundException(String.format("Не найдена цель клиента: id = %s", goalMinimalDTO.id));
        });
    }

    public Goal findGoalById(UUID goalId) {
        log.debug("#findGoalById: goalId={}", goalId);
        return goalRepository.findById(goalId).orElseThrow(
                () -> new NotFoundException("Goal", "goalId", goalId.toString()));
    }

    public List<GoalMinimalDto> getGoals(UUID clientId) {
        log.debug("#getGoals: clientId={}", clientId);
        clientRepository.findById(clientId).orElseThrow(
                () -> new NotFoundException(String.format("Клиент с id '%s' не найден.", clientId))
        );

        return goalRepository.findAllByClientId(clientId).stream()
                .map(goal -> GoalMinimalDto.builder()
                        .id(goal.getId())
                        .name(goal.getName())
                        .build())
                .collect(Collectors.toList());
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
