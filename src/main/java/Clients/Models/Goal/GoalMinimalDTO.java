package Clients.Models.Goal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
@JsonPropertyOrder({"id",
                    "goalName",
                    "goalDescription",
                    "desiredCompletionDate",
                    "isCompleted",
                    "completionDate"})
public class GoalMinimalDTO extends GoalDTO {
    @JsonProperty("goalDescription")
    public String goalDescription;
    @JsonProperty("desiredCompletionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate desiredCompletionDate;
    @JsonProperty("isCompleted")
    public boolean isCompleted;
    @JsonProperty("completionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate completionDate;
}
