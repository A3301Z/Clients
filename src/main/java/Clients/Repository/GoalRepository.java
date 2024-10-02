package Clients.Repository;

import Clients.Entity.Goal.Goal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GoalRepository extends CrudRepository<Goal, UUID> {
    List<Goal> findAllByClientId(UUID clientId);
}