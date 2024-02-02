package Clients.Repository;

import Clients.Entity.Client.Client;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ClientRepository {
    private List<Client> clients = new ArrayList<>();
    Client client_1 = new Client();
    Client client_2 = new Client();

     {
        client_1.setId(UUID.randomUUID());
        client_1.setFirstname("Анатолий");
        client_1.setLastname("Никифоров");
        client_1.setSurname("Игоревич");
        client_1.setSex("мужской");
        client_1.setBirthday(LocalDate.of(1996, 11, 29));
        client_1.setAdditionalNumber(null);
        client_1.setEmail("nikiforov@mail.com");
        client_1.setPhoneNumber("999 999 99 99");
        client_1.setBlock(false);
        client_1.setReasonOfBlock("пернул");


        client_2.setId(UUID.randomUUID());
        client_2.setFirstname("Андрей");
        client_2.setLastname("Витальев");
        client_2.setSurname("Петрович");
        client_2.setSex("женский");
        client_2.setBirthday(LocalDate.of(1912, 10, 11));
        client_2.setAdditionalNumber(null);
        client_2.setEmail("ppp@mail.com");
        client_2.setPhoneNumber("111 111 11 11");
        client_2.setBlock(true);
        client_2.setReasonOfBlock("свистнул");
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Client> getClients()  {
        clients.add(client_1);
        clients.add(client_2);
        return clients;
    }
}
