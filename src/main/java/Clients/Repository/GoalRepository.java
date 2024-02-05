package Clients.Repository;

import Clients.Entity.Client.Client;
import Clients.Entity.Goal.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GoalRepository extends JpaRepository<Goal, UUID> {

}