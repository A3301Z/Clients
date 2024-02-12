package Clients.Models.Goal;

import Clients.Models.Views.Private;
import Clients.Models.Views.Public;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDate;
@JsonPropertyOrder({"id",
                    "goalName",
                    "goalDescription",
                    "desiredCompletionDate",
                    "isCompleted",
                    "completionDate"})
public class GoalMinimalDTO extends GoalDTO {
    @JsonProperty("goalDescription")
    @JsonView(Public.class)
    public String goalDescription;
    @JsonProperty("desiredCompletionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonView(Public.class)
    public LocalDate desiredCompletionDate;
    @JsonProperty("isCompleted")
    @JsonView(Private.class)
    public boolean isCompleted;
    @JsonProperty("completionDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonView(Private.class)
    public LocalDate completionDate;
}
