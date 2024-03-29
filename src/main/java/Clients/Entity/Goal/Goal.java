package Clients.Entity.Goal;

import Clients.Entity.Client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Goal() {

    }

    public Goal(UUID id, String goalName, String goalDescription, LocalDate desiredCompletionDate, boolean isCompleted,
                LocalDate completionDate) {
        this.id                    = id;
        this.goalName              = goalName;
        this.goalDescription       = goalDescription;
        this.desiredCompletionDate = desiredCompletionDate;
        this.isCompleted           = isCompleted;
        this.completionDate        = completionDate;
    }

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("goalName")
    public String getGoalName() {
        return goalName;
    }

    @JsonProperty("goalDescription")
    public String getGoalDescription() {
        return goalDescription;
    }

    @JsonProperty("desiredCompletionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate getDesiredCompletionDate() {
        return desiredCompletionDate;
    }

    @JsonProperty("isCompleted")
    public boolean isCompleted() {
        return isCompleted;
    }

    @JsonProperty("completionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public void setDesiredCompletionDate(LocalDate desiredCompletionDate) {
        this.desiredCompletionDate = desiredCompletionDate;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }
}
