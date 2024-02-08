package Clients;

import Clients.Controller.ClientController;
import Clients.Entity.Client.Client;
import Clients.Models.Client.ClientDTO;
import Clients.Models.Client.ClientMinimalDTO;
import Clients.Service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClientControllerTests {
    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void testAdd() {
        ClientDTO dto = new ClientDTO();
        clientController.add(dto);
        verify(clientService, times(1)).add(dto);
    }
    @Test
    void testGetClients() {
        List<Client> clients = new ArrayList<>();
        when(clientService.getClients()).thenReturn(clients);
        List<ClientMinimalDTO> actualClients = clientController.getClients();
        assertEquals(clients.size(), actualClients.size());
    }

    @Test
    void testGetFullInfo() {
        UUID id = UUID.randomUUID();
        Client client = new Client();
        when(clientService.findById(id)).thenReturn(Optional.of(client));
        clientController.getFullInfo(id);
        verify(clientService, times(1)).findById(id);
    }

    @Test
    void testUpdateClient() {
        Client client = new Client();
        ClientDTO dto = new ClientDTO();
        clientController.updateClient(dto);
        verify(clientService, times(1)).updateClient(client);
    }

    @Test
    void testBlockingClient() {
        UUID id = UUID.randomUUID();
        clientController.blockingClient(id);
        verify(clientService, times(1)).blockingClient(id);
    }
}
