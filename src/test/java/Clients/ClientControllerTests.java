package Clients;

import Clients.rest.Controller.ClientController;
import Clients.Entity.Client;
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
    void testGetClients() {
        List<ClientMinimalDTO> clients = new ArrayList<>();
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
    void testBlockingClient() {
        UUID id = UUID.randomUUID();
        clientController.blockingClient(id, "Ссыт в бассейн!");
        verify(clientService, times(1)).blockingClient(id, "Ссыт в бассейн!");
    }
}
