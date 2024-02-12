package Clients.Controller;

import Clients.Entity.Client.Client;
import Clients.Models.Client.ClientDTO;
import Clients.Models.Client.ClientMinimalDTO;
import Clients.Models.Views.Public;
import Clients.Service.ClientService;
import com.fasterxml.jackson.annotation.JsonView;
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

    public ClientController(@Autowired ClientService clientService) {
        this.clientService = clientService;
    }

    @Tag(name = "КЛИЕНТ: Добавить в список.")
    @PostMapping(value = "/client")
    public void add(@RequestBody @JsonView(Public.class) ClientDTO clientDTO) {

        Client client = new Client();
        client.setId(UUID.randomUUID());
        client.setFirstname(clientDTO.firstname);
        client.setLastname(clientDTO.lastname);
        client.setSurname(clientDTO.surname);
        client.setSex(clientDTO.sex);
        client.setBirthday(clientDTO.birthday);
        client.setPhoneNumber(clientDTO.phoneNumber);
        client.setBlockStatus(false);
        client.setAdditionalNumber(clientDTO.additionalNumber);
        client.setEmail(clientDTO.email);
        client.setReasonOfBlock(null);

        clientService.add(client);
    }

    @Tag(name = "КЛИЕНТ: Получить список.")
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

    @Tag(name = "КЛИЕНТ: Полная информация.")
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> getFullInfo(@PathVariable UUID id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Tag(name = "КЛИЕНТ: Изменить параметры.")
    @PutMapping(value = "/client")
    public void updateClient(@RequestBody() ClientDTO clientDTO) {
        Client client = new Client();

        client.setId(clientDTO.id);
        client.setLastname(clientDTO.lastname);
        client.setFirstname(clientDTO.firstname);
        client.setSurname(clientDTO.surname);
        client.setSex(clientDTO.sex);
        client.setBirthday(clientDTO.birthday);
        client.setPhoneNumber(clientDTO.phoneNumber);
        client.setAdditionalNumber(clientDTO.additionalNumber);
        client.setEmail(clientDTO.email);
        client.setBlockStatus(clientDTO.is_block);
        client.setReasonOfBlock(clientDTO.reasonOfBlock);

        clientService.updateClient(client);
    }

    @Tag(name = "КЛИЕНТ: Заблокировать.")
    @DeleteMapping("/client/{id}/block")
    public void blockingClient(@PathVariable UUID id, @RequestParam String reasonOfBlock) {
        clientService.blockingClient(id, reasonOfBlock);
    }

    @Tag(name = "КЛИЕНТ: Удалить.")
    @DeleteMapping("/client/{id}/delete")
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }

    @Tag(name = "ФОТО: Добавить фото клиента.")
    @PostMapping(value = "/client/{id}/photo", consumes = "multipart/form-data")
    public void addPhoto(@PathVariable UUID id, @RequestParam() MultipartFile file) throws IOException {
        clientService.addPhoto(id, file.getBytes());
    }

    @Tag(name = "ФОТО: Получить фото клиента.")
    @GetMapping(value = "/client/{id}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getPhoto(@PathVariable UUID id) {
        return this.clientService.getPhoto(id);
    }

}
