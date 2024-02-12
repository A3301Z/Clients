package Clients.Service;

import Clients.Entity.Client.Client;
import Clients.Models.Client.ClientDTO;
import Clients.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(@Autowired ClientRepository repository) {
        this.clientRepository = repository;
    }

    @Transactional
    public void add(Client client) {
        clientRepository.save(client);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void updateClient(Client client) {

        Optional<Client> optionalClient = findById(client.getId());

        if (optionalClient.isPresent()) {
            Client updatedClient = optionalClient.get();
            updatedClient.setFirstname(client.getFirstname());
            updatedClient.setLastname(client.getLastname());
            updatedClient.setSurname(client.getSurname());
            updatedClient.setBirthday(client.getBirthday());
            updatedClient.setSex(client.getSex());
            updatedClient.setPhoneNumber(client.getPhoneNumber());
            updatedClient.setEmail(client.getEmail());
            updatedClient.setAdditionalNumber(client.getAdditionalNumber());
            updatedClient.setBlockStatus(client.getBlockStatus());
            updatedClient.setReasonOfBlock(client.getReasonOfBlock());

            clientRepository.save(client);

        } else {
            throw new RuntimeException(String.format("Тренер с id '%s' не существует.", client.getId()));
        }
    }

    public void blockingClient(UUID id, String reasonOfBlock) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setReasonOfBlock(reasonOfBlock);
            client.setBlockStatus(true);
            clientRepository.save(client);
        } else {
            throw new RuntimeException(String.format("Клиент с id '%s' не найден.", id));
        }
    }

    public void deleteClient(UUID id) {
        Optional<Client> optionalClient = findById(id);
        if (optionalClient.isEmpty()) {
            throw new RuntimeException(String.format("Клиент с ID '%s' не найден. Не удалось удалить.", id));
        } else {
            Client client = optionalClient.get();
            clientRepository.delete(client);
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
}
