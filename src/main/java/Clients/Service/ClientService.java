package Clients.Service;

import Clients.Entity.Client.Client;
import Clients.Entity.Goal.Goal;
import Clients.Models.Client.ClientDTO;
import Clients.Models.Goal.GoalDTO;
import Clients.Repository.ClientRepository;
import Clients.Repository.GoalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final GoalRepository goalRepository;

    public ClientService(@Autowired ClientRepository repository, GoalRepository goalRepository) {
        this.clientRepository = repository;
        this.goalRepository   = goalRepository;
    }

    public void add(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setFirstname(clientDTO.firstname);
        client.setLastname(clientDTO.lastname);
        client.setSurname(clientDTO.surname);
        client.setSex(clientDTO.sex);
        client.setBirthday(clientDTO.birthday);
        client.setPhoneNumber(clientDTO.phoneNumber);
        client.setBlockStatus(clientDTO.is_block);
        client.setAdditionalNumber(clientDTO.additionalNumber);
        client.setEmail(clientDTO.email);
        client.setReasonOfBlock(clientDTO.reasonOfBlock);

        clientRepository.save(client);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getById(UUID id) {
        return clientRepository.findById(id);
    }

    public void updateClient(ClientDTO clientDTO) {

        Optional<Client> optionalClient = getById(clientDTO.id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setFirstname(clientDTO.firstname);
            client.setLastname(clientDTO.lastname);
            client.setSurname(clientDTO.surname);
            client.setBirthday(clientDTO.birthday);
            client.setSex(clientDTO.sex);
            client.setPhoneNumber(clientDTO.phoneNumber);
            client.setEmail(clientDTO.email);
            client.setAdditionalNumber(clientDTO.additionalNumber);
            client.setBlockStatus(clientDTO.is_block);
            client.setReasonOfBlock(clientDTO.reasonOfBlock);

            clientRepository.save(client);

        } else {
            throw new RuntimeException(String.format("Тренер с id '%s' не существует.", clientDTO.id));
        }
    }

    public void blockingClient(UUID id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setBlockStatus(true);
            clientRepository.save(client);
        } else {
            throw new RuntimeException(String.format("Клиент с id '%s' не найден.", id));
        }
    }

    public void addPhoto(UUID id, byte[] content) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isEmpty()) {
            throw new RuntimeException(String.format("Не удалось добавить фото. Клиент с id '%s' не найден.", id));
        } else {
            Client client = optionalClient.get();
            client.setContent(content);
            clientRepository.save(client);
        }
    }

    public byte[] getPhoto(UUID id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isEmpty()) {
            throw new RuntimeException(String.format("Не удалось загрузить фото. Клиент с id '%s' не найден.", id));
        } else
            return optionalClient.get().getContent();
    }

    @Transactional
    public void addGoal(UUID clientId, GoalDTO goalDTO) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            throw new RuntimeException(String.format("Не удалось добавить цель. Клиент с id '%s' не найден.",
                                                     clientId));
        } else {
            Goal goal = new Goal();
            goal.setId(UUID.randomUUID());
            goal.setClient(client.get());
            goal.setGoalName(goalDTO.goalName);
            goal.setGoalDescription(goalDTO.goalDescription);
            goal.setDesiredCompletionDate(goalDTO.desiredCompletionDate);
            goal.setCompleted(goalDTO.isCompleted);
            goal.setCompletionDate(goalDTO.completionDate);

            goalRepository.save(goal);
        }
    }

    public List<Goal> getGoals(UUID clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            throw new RuntimeException(String.format("Клиент с id '%s' не найден.", clientId));
        } else {
            List<Goal> goalList = goalRepository.findAllByClient_Id(clientId);

            return goalList.stream().map(goal -> {
                Goal newGoal = new Goal();
                newGoal.setId(goal.getId());
                newGoal.setGoalName(goal.getGoalName());
                newGoal.setGoalDescription(goal.getGoalDescription());
                newGoal.setDesiredCompletionDate(goal.getDesiredCompletionDate());
                newGoal.setCompleted(goal.isCompleted());
                newGoal.setCompletionDate(goal.getCompletionDate());
                return newGoal;
            }).collect(Collectors.toList());
        }
    }
}
