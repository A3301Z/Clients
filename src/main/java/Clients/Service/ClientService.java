package Clients.Service;

import Clients.Entity.Client;
import Clients.Exception.NotFoundException;
import Clients.Models.Client.ClientDto;
import Clients.Models.Client.ClientMinimalDTO;
import Clients.Models.Client.CreateClientDto;
import Clients.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Добавить клиента в БД
     */
    @Transactional
    public void add(CreateClientDto createClientDto) {
        log.debug("#add: clientDto={}", createClientDto);
        clientRepository.save(Client.toClient(createClientDto));
    }

    /**
     * Получить список всех клиентов
     */
    public List<ClientMinimalDTO> getClients() {
        log.debug("#getClients");
        return clientRepository.findAllClients().stream().map(client -> ClientMinimalDTO.builder()
                        .id(client.getId())
                        .firstname(client.getFirstname())
                        .lastname(client.getLastname())
                        .surname(client.getSurname())
                        .birthday(client.getBirthday())
                        .sex(client.getSex())
                        .phoneNumber(client.getPhoneNumber())
                        .isBlock(client.isBlock())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * Получить клиента по идентификатору
     */
    public Optional<Client> findById(UUID id) {
        log.debug("#findById: clientId = {}", id);
        return clientRepository.findById(id);
    }

    /**
     * Обновить параметры клиента
     */
    @Transactional
    public void updateClient(ClientDto clientDto) {
        log.debug("#updateClient: clientDto={}", clientDto);
        clientRepository.findById(clientDto.id).orElseThrow(
                () -> new NotFoundException("Client", "id", clientDto.id.toString())
        );
        clientRepository.save(Client.toClient(clientDto));
    }

    /**
     * Блокировать аккаунт клиента
     */
    public void blockingClient(UUID id, String reasonOfBlock) {
        log.debug("#blockingClient: id={}, reasonOfBlock={}", id, reasonOfBlock);
        clientRepository.findById(id).ifPresentOrElse(client -> {
            client.setBlock(true);
            client.setReasonOfBlock(reasonOfBlock);
            clientRepository.save(client);
        }, () -> {
            throw new NotFoundException("Client", "id", id.toString());
        });
    }

    /**
     * Удалить клиента из БД
     */
    public void deleteClient(UUID id) {
        log.debug("#deleteClient: id={}", id);
        clientRepository.findById(id).ifPresentOrElse(clientRepository::delete, () -> {
            throw new NotFoundException("Client", "id", id.toString());
        });
    }

    /**
     * Добавить фото клиента
     */
    public void addPhoto(UUID id, byte[] content) {
        log.debug("#addPhoto: id = {}", id);
        clientRepository.findById(id).ifPresentOrElse(client -> {
            client.setContent(content);
            clientRepository.save(client);
        }, () -> {
            throw new NotFoundException(String.format("Фото не добавлено, клиент с ID = %s не найден", id));
        });
    }

    /**
     * Получить фото клиента
     */
    public byte[] getPhoto(UUID id) {
        log.debug("#getPhoto: id = {}", id);
        return clientRepository.findById(id)
                .map(Client::getContent)
                .orElseThrow(() -> new NotFoundException(String.format("Фото не найдено, клиент с ID = %s не найден", id)));
    }
}
