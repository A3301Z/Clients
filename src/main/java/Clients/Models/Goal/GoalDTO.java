package Clients.Models.Goal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.UUID;
@JsonPropertyOrder({"id",
                    "client_id",
                    "goalName",
                    "goalDescription",
                    "desiredCompletionDate",
                    "isCompleted",
                    "completionDate"})
public class GoalDTO {
    @JsonProperty("id")
    public UUID id;
    @JsonProperty("goalName")
    public String goalName;
    @JsonProperty("goalDescription")
    public String goalDescription;
    @JsonProperty("desiredCompletionDate")
    public LocalDate desiredCompletionDate;
    @JsonProperty("isCompleted")
    public boolean isCompleted;
    @JsonProperty("completionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate completionDate;
}
