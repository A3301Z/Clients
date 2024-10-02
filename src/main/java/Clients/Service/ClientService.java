package Clients.Service;

import Clients.Entity.Client.Client;
import Clients.Exception.NotFoundException;
import Clients.Models.Client.ClientDto;
import Clients.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    /**
     * Добавить клиента в БД
     */
    @Transactional
    @CachePut(value = "client", key = "#client.id")
    public void add(ClientDto clientDto) {
        log.debug("#add: clientDto={}", clientDto);
        clientRepository.save(Client.toClient(clientDto));
    }

    /**
     * Получить список всех клиентов
     */
    @Cacheable(value = "clients", key = "'allClients'")
    public List<Client> getClients() {
        log.debug("#getClients");
        return clientRepository.findAll();
    }

    /**
     * Получить клиента по идентификатору
     */
    @Cacheable(value = "client", key = "#id")
    public Optional<Client> findById(UUID id) {
        log.debug("#findById");
        return clientRepository.findById(id);
    }

    /**
     * Обновить параметры клиента
     */
    @Transactional
    @CachePut(value = "client", key = "#client.id")
    public void updateClient(ClientDto clientDto) {
        log.debug("#updateClient: clientDto={}", clientDto);
        Client client = clientRepository.findById(clientDto.id).orElseThrow(
                () -> new NotFoundException("Client", "id", clientDto.id.toString())
        );

        BeanUtils.copyProperties(clientDto, client);
        clientRepository.save(client);
    }

    /**
     * Блокировать аккаунт клиента
     */
    @CachePut(value = "client", key = "#id")
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
    @CacheEvict(value = "client", key = "#id")
    public void deleteClient(UUID id) {
        clientRepository.findById(id).ifPresentOrElse(clientRepository::delete, () -> {
            throw new NotFoundException("Client", "id", id.toString());
        });
    }

    /**
     * Добавить фото клиента
     */
    public void addPhoto(UUID id, byte[] content) {
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
        if (clientRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Client", "id", id.toString());
        } else
            return clientRepository.findById(id).get().getContent();
    }
}
