package Clients.Controller;

import Clients.Entity.Client.Client;
import Clients.Models.Client.ClientDTO;
import Clients.Models.Client.ClientMinimalDTO;
import Clients.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClientController {
    private final ClientService service;

    public ClientController(@Autowired ClientService service) {
        this.service = service;
    }

    @PostMapping("/client")
    public void add(@RequestBody ClientDTO clientDTO) {
        service.add(clientDTO);
    }

    @GetMapping("/clients")
    public List<ClientMinimalDTO> getClients() {
        List<ClientMinimalDTO> clients = new ArrayList<>();
        for (Client client : service.getClients()) {
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

    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getFullInfo(@PathVariable UUID id) {
        Optional<Client> client = service.getById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/client")
    public void updateClient(@RequestBody ClientDTO clientDTO) {
        service.updateClient(clientDTO);
    }
    @DeleteMapping("/client/{id}")
    public void blockingClient(@PathVariable UUID id) {
        service.blockingClient(id);
    }
}
