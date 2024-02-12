package Clients.Service;

import Clients.Entity.Client.Client;
import Clients.Entity.Goal.Goal;
import Clients.Models.Goal.GoalDTO;
import Clients.Models.Goal.GoalMinimalDTO;
import Clients.Repository.ClientRepository;
import Clients.Repository.GoalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class GoalService {
    private final GoalRepository goalRepository;
    private final ClientRepository clientRepository;

    public GoalService(@Autowired ClientRepository clientRepository, @Autowired GoalRepository goalRepository) {
        this.goalRepository   = goalRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void addGoal(Goal goal) {
        goalRepository.save(goal);
    }

    @Transactional
    public void updateGoal(Goal goal) {
        goalRepository.save(goal);
    }

    public Goal findGoalById(UUID goalId) {
        Optional<Goal> goalOptional = goalRepository.findById(goalId);

        if (goalOptional.isEmpty()) {
            throw new RuntimeException(String.format("Цель с id '%s' не найдена.", goalId));
        } else {
            return goalOptional.get();
        }
    }

    public List<GoalDTO> getGoals(UUID clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            throw new RuntimeException(String.format("Клиент с id '%s' не найден.", clientId));
        } else {

            List<Goal> goalList = goalRepository.findAllByClient_Id(clientId);
            List<GoalDTO> goalDTOList = new ArrayList<>();

            for (Goal goal : goalList) {
                GoalDTO goalDTO = new GoalDTO();
                goalDTO.id       = goal.getId();
                goalDTO.goalName = goal.getGoalName();
                goalDTOList.add(goalDTO);
            }
            return goalDTOList;
        }
    }

    @Transactional
    public void completeGoal(UUID goalId) {
        Goal goal = findGoalById(goalId);
        goal.setCompleted(true);
        goal.setCompletionDate(LocalDate.now().atStartOfDay(ZoneOffset.UTC).toLocalDate());
        goalRepository.save(goal);
    }

    @Transactional
    public void doNotComplete(UUID goalId) {
        Goal goal = findGoalById(goalId);
        goal.setCompleted(false);
        goal.setCompletionDate(null);
        goalRepository.save(goal);
    }

    public Goal getFullInfo(UUID goalId) {
        return findGoalById(goalId);
    }

    public void deleteGoal(UUID goalId) {
        Goal goal = findGoalById(goalId);
        goalRepository.delete(goal);
    }
}
