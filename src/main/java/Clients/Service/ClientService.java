package Clients.Service;

import Clients.Entity.Client.Client;
import Clients.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(@Autowired ClientRepository repository) {
        this.repository = repository;
    }
    
    public void add(Client client) {
        repository.addClient(client);
    }
    
    public List<Client> getClients() {
        return repository.getClients();
    }
}
