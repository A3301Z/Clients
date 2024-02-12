package Clients.Models.Goal;

import Clients.Models.Views.Public;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({"id", "goalName"})
public class GoalDTO {

    @JsonProperty("id")
    @JsonView(Public.class)
    public UUID id;
    @JsonProperty("goalName")
    @JsonView(Public.class)
    public String goalName;

}
