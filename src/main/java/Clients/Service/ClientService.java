package Clients.Service;

import Clients.Entity.Client.Client;
import Clients.Models.Client.ClientDTO;
import Clients.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(@Autowired ClientRepository repository) {
        this.repository = repository;
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

        repository.save(client);
    }

    public List<Client> getClients() {
        return repository.findAll();
    }

    public Optional<Client> getById(UUID id) {
        return repository.findById(id);
    }

    public void updateClient(ClientDTO clientDTO) {

        Optional<Client> updatedClient = getById(clientDTO.id);

        if (updatedClient.isPresent()) {
            Client client1 = updatedClient.get();
            client1.setFirstname(clientDTO.firstname);
            client1.setLastname(clientDTO.lastname);
            client1.setSurname(clientDTO.surname);
            client1.setBirthday(clientDTO.birthday);
            client1.setSex(clientDTO.sex);
            client1.setPhoneNumber(clientDTO.phoneNumber);
            client1.setEmail(clientDTO.email);
            client1.setAdditionalNumber(clientDTO.additionalNumber);
            client1.setBlockStatus(clientDTO.is_block);
            client1.setReasonOfBlock(clientDTO.reasonOfBlock);

            repository.save(client1);

        } else {
            throw new RuntimeException(String.format("Тренер с id '%s' не существует.", clientDTO.id));
        }
    }

    public void blockingClient(UUID id) {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setBlockStatus(true);
            repository.save(client);
        } else {
            throw new RuntimeException(String.format("Клиент с id '%s' не найден.", id));
        }
    }
}
