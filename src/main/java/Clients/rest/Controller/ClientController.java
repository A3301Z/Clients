package Clients.rest.Controller;

import Clients.Entity.Client;
import Clients.Models.Client.ClientDto;
import Clients.Models.Client.ClientMinimalDTO;
import Clients.Models.Client.CreateClientDto;
import Clients.Service.ClientService;
import Clients.rest.api.ClientApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final ClientService clientService;

    @Override
    public void add(CreateClientDto createClientDto) {
        clientService.add(createClientDto);
    }

    @Override
    public List<ClientMinimalDTO> getClients() {
        return clientService.getClients();
    }

    @Override
    public ResponseEntity<Client> getFullInfo(UUID id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public void updateClient(ClientDto clientDTO) {
        clientService.updateClient(clientDTO);
    }

    @Override
    public void blockingClient(UUID id, String reasonOfBlock) {
        clientService.blockingClient(id, reasonOfBlock);
    }

    @Override
    public void deleteClient(UUID id) {
        clientService.deleteClient(id);
    }

    @Override
    public void addPhoto(UUID id, MultipartFile file) throws IOException {
        clientService.addPhoto(id, file.getBytes());
    }

    @Override
    public @ResponseBody byte[] getPhoto(UUID id) {
        return this.clientService.getPhoto(id);
    }
}
