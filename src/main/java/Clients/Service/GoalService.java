package Clients.Service;

import Clients.Entity.Goal;
import Clients.Exception.NotFoundException;
import Clients.Models.Goal.CreateGoalDto;
import Clients.Models.Goal.GoalDto;
import Clients.Models.Goal.GoalMinimalDto;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public void addGoal(UUID clientID, CreateGoalDto createGoalDto) {
        log.debug("#addGoal: clientId = {} goalMinimalDto = {}", clientID, createGoalDto);

        clientRepository.findById(clientID).ifPresent(client -> {
            goalRepository.save(
                    Goal.builder()
                            .name(createGoalDto.name)
                            .description(createGoalDto.description)
                            .desiredCompletionDate(createGoalDto.desiredCompletionDate)
                            .clientId(clientID)
                            .build()
            );
        });
    }

    @Transactional
    public void updateGoal(UUID clientId, GoalDto goalDto) {
        log.debug("#updateGoal: goalMinimalDTO={}", goalDto);
        goalRepository.findById(goalDto.id).ifPresentOrElse(goal -> {
            goalRepository.save(
                    Goal.builder()
                            .id(goalDto.id)
                            .clientId(clientId)
                            .name(goal.getName())
                            .description(goal.getDescription())
                            .isCompleted(goal.isCompleted())
                            .desiredCompletionDate(goalDto.desiredCompletionDate)
                            .build()
            );
        }, () -> {
            throw new NotFoundException(String.format("Не найдена цель клиента: id = %s", goalDto.id));
        });
    }


    public List<GoalMinimalDto> getGoals(UUID clientId) {
        log.debug("#getGoals: clientId={}", clientId);
        clientRepository.findById(clientId).orElseThrow(
                () -> new NotFoundException(String.format("Клиент с id '%s' не найден.", clientId))
        );

        return goalRepository.findAllByClientId(clientId).stream()
                .map(goal -> GoalMinimalDto.builder()
                        .id(goal.getId())
                        .description(goal.getDescription())
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

    public GoalDto getFullInfo(UUID goalId) {
        log.debug("#getFullInfo: goalId={}", goalId);

        return goalRepository.findById(goalId).map(goal ->
            GoalDto.builder()
                    .id(goalId)
                    .name(goal.getName())
                    .description(goal.getDescription())
                    .desiredCompletionDate(goal.getDesiredCompletionDate())
                    .isCompleted(goal.isCompleted())
                    .completionDate(goal.getCompletionDate())
                    .build())
                .orElseThrow(() -> new NotFoundException("Goal", "goalId", goalId.toString()));

    }

    public void deleteGoal(UUID goalId) {
        log.debug("#deleteGoal: goalId={}", goalId);
        goalRepository.findById(goalId).ifPresentOrElse(goalRepository::delete, () -> {
            throw new NotFoundException(String.format("Цель с id '%s' не найдена.", goalId));
        });
    }
}
