package Clients.Models.Goal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({"id", "goalName"})
public class GoalDTO {
    @JsonProperty("id")
    public UUID id;
    @JsonProperty("goalName")
    public String goalName;
}
