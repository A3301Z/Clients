package Clients.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "goals")
public class Goal implements Serializable {

    /**
     * Идентификатор
     */
    @Id
    @Column("id")
    private UUID id;

    /**
     * Имя цели (Напирмер "похудеть до конца года")
     */
    @Column("goal_name")
    private String name;

    /**
     * Описание
     */
    @Column("goal_description")
    private String description;

    /**
     * Желаемая дата завершения
     */
    @Column("desired_completion_date")
    private LocalDate desiredCompletionDate;

    /**
     * Флаг завершенности
     */
    @Column("is_completed")
    private boolean isCompleted;

    /**
     * Дата завершения
     */
    @Column("completion_date")
    private LocalDate completionDate;

    /**
     * уникальный идентификатор клиента
     */
    @Column("client_id")
    private UUID clientId;

    public GoalBuilder toBuilder() {
        return Goal.builder().
                name(this.name)
                .description(this.description)
                .desiredCompletionDate(this.desiredCompletionDate)
                .isCompleted(this.isCompleted)
                .completionDate(this.completionDate);
    }
}
