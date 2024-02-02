package Clients.Controller;

import Clients.Entity.Client.Client;
import Clients.Models.Client.ClientDTO;
import Clients.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    private final ClientService service;

    public ClientController(@Autowired ClientService service) {
        this.service = service;
    }

    @PostMapping("/client")
    public void add(Client client) {
        service.add(client);
    }

    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        List<ClientDTO> clients = new ArrayList<>();
        for (Client client : service.getClients()) {
            ClientDTO dto = new ClientDTO();
            dto.id = client.getId();
            dto.firstname = client.getFirstname();
            dto.lastname = client.getLastname();
            clients.add(dto);
        }
        return clients;
    }
}
