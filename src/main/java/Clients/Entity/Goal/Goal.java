package Clients.Entity.Goal;

import Clients.Entity.Client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@RequiredArgsConstructor
@JsonPropertyOrder({"id",
                    "client_id",
                    "goalName",
                    "goalDescription",
                    "desiredCompletionDate",
                    "isCompleted",
                    "completionDate"})
public class Goal implements Serializable {
    @Id
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("goalName")
    private String goalName;
    @JsonProperty("goalDescription")
    private String goalDescription;
    @JsonProperty("desiredCompletionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate desiredCompletionDate;
    @JsonProperty("isCompleted")
    private boolean isCompleted;
    @JsonProperty("completionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate completionDate;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;
}
