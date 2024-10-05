package Clients.Repository;

import Clients.Entity.Client;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {

    /**
     * Найти всех клиентов и вернуть минимальную информацию о каждом
     * @return ClientMinimalDTO объект содержащий минимальную информацию о клиенте
     */
    @Query("select * from Clients")
    List<Client> findAllClients();
}
