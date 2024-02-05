package Clients.Controller;

import Clients.Entity.Client.Client;
import Clients.Entity.Goal.Goal;
import Clients.Models.Client.ClientDTO;
import Clients.Models.Client.ClientMinimalDTO;
import Clients.Models.Goal.GoalDTO;
import Clients.Service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(@Autowired ClientService service) {
        this.clientService = service;
    }

    @Tag(name = "Добавить клиента.")
    @PostMapping(value = "/client")
    public void add(@RequestBody ClientDTO clientDTO) {
        clientService.add(clientDTO);
    }

    @Tag(name = "Список клиентов.")
    @GetMapping("/clients")
    public List<ClientMinimalDTO> getClients() {
        List<ClientMinimalDTO> clients = new ArrayList<>();
        for (Client client : clientService.getClients()) {
            ClientMinimalDTO dto = new ClientMinimalDTO();
            dto.id          = client.getId();
            dto.lastname    = client.getLastname();
            dto.firstname   = client.getFirstname();
            dto.surname     = client.getSurname();
            dto.sex         = client.getSex();
            dto.birthday    = client.getBirthday();
            dto.phoneNumber = client.getPhoneNumber();
            dto.is_block    = client.getBlockStatus();

            clients.add(dto);
        }
        return clients;
    }

    @Tag(name = "Полная информация о клиенте.")
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getFullInfo(@PathVariable UUID id) {
        Optional<Client> client = clientService.getById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Tag(name = "Изменить параметры килента.")
    @PutMapping(value = "/client")
    public void updateClient(@RequestBody() ClientDTO clientDTO) {
        clientService.updateClient(clientDTO);
    }

    @Tag(name = "Заблокировать клиента.")
    @DeleteMapping("/client/{id}")
    public void blockingClient(@PathVariable UUID id) {
        clientService.blockingClient(id);
    }


    @Tag(name = "Добавить фото клиента.")
    @PostMapping(value = "/client/{id}/photo", consumes = "multipart/form-data")
    public void addPhoto(@PathVariable UUID id, @RequestParam() MultipartFile file) throws IOException {
        clientService.addPhoto(id, file.getBytes());
    }

    @Tag(name = "Получить фото клиента.")
    @GetMapping(value = "/client/{id}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] addPhoto(@PathVariable UUID id) {
        return this.clientService.getPhoto(id);
    }
    @Tag(name = "Добавить цель в список.")
    @PostMapping("/client/{id}/newGoal")
    public void addGoals(@PathVariable UUID id, @RequestBody GoalDTO goalDTO) {
        clientService.addGoal(id, goalDTO);
    }

    @Tag(name = "Получить список целей клиента.")
    @GetMapping("/client/{clientId}/goals")
    public List<Goal> getGoals(@PathVariable UUID clientId) {
        return clientService.getGoals(clientId);
    }
}
