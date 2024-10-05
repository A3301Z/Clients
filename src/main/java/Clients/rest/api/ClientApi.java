package Clients.rest.api;

import Clients.Entity.Client;
import Clients.Models.Client.ClientDto;
import Clients.Models.Client.ClientMinimalDTO;
import Clients.Models.Client.CreateClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Tag(name = "API клиентов")
@RequestMapping("/api/v3/clients")
public interface ClientApi {
    @Operation(summary = "Добавить в список")
    @PostMapping(value = "/client")
    void add(@RequestBody CreateClientDto createClientDto);

    @Operation(summary = "Получить список")
    @GetMapping("/clients")
    List<ClientMinimalDTO> getClients();

    @Operation(summary = "Полная информация")
    @GetMapping("/clients/{id}")
    ResponseEntity<Client> getFullInfo(@PathVariable UUID id);

    @Operation(summary = "Изменить параметры")
    @PutMapping(value = "/clients")
    void updateClient(@RequestBody() ClientDto clientDTO);

    @Operation(summary = "Заблокировать")
    @DeleteMapping("/clients/{id}/block")
    void blockingClient(@PathVariable UUID id, @RequestParam String reasonOfBlock);

    @Operation(summary = "Удалить")
    @DeleteMapping("/clients/{id}/delete")
    void deleteClient(@PathVariable UUID id);

    @Operation(summary = "Добавить фото клиента")
    @PostMapping(value = "/clients/{id}/photo", consumes = "multipart/form-data")
    void addPhoto(@PathVariable UUID id, @RequestParam() MultipartFile file) throws IOException;

    @Operation(summary = "Получить фото клиента")
    @GetMapping(value = "/clients/{id}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody byte[] getPhoto(@PathVariable UUID id);
}
