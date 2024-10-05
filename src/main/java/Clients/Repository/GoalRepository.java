package Clients.Repository;

import Clients.Entity.Goal;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GoalRepository extends CrudRepository<Goal, UUID> {
    List<Goal> findAllByClientId(UUID clientId);

    @Modifying
    @Query(value = """
            INSERT INTO goals (
                id, completion_date, desired_completion_date,
                goal_description, goal_name, is_completed,
                client_id, description, name
            )
            VALUES (
                :#{#goal.id}, :#{#goal.completionDate}, :#{#goal.desiredCompletionDate},
                :#{#goal.goalDescription}, :#{#goal.goalName}, :#{#goal.isCompleted},
                :#{#goal.description}, :#{#goal.name}, :client_id)""")
    void addGoal(@Param("client_id") UUID clientId, @Param("goal") Goal goal);
}