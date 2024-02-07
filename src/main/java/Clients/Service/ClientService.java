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

    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void updateClient(ClientDTO clientDTO) {

        Optional<Client> optionalClient = findById(clientDTO.id);

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
}
