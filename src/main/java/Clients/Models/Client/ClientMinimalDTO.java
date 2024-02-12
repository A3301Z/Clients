package Clients.Models.Client;

import Clients.Models.Views.Private;
import Clients.Models.Views.Public;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({"id",
                    "lastname",
                    "firstname",
                    "surname",
                    "sex",
                    "birthday",
                    "phoneNumber",
                    "email",
                    "additionalNumber"})
public class ClientMinimalDTO {
    @JsonView(Public.class)
    @JsonProperty("id")
    public UUID id;
    @JsonProperty("firstname")
    @JsonView(Public.class)
    public String firstname;
    @JsonProperty("lastname")
    @JsonView(Public.class)
    public String lastname;
    @JsonProperty("surname")
    @JsonView(Public.class)
    public String surname;
    @JsonFormat(pattern = "dd.MM.yyyy")
    @JsonView(Public.class)
    @JsonProperty("birthday")
    public LocalDate birthday;
    @JsonProperty("sex")
    @JsonView(Public.class)
    public String sex;
    @JsonProperty("phoneNumber")
    @JsonView(Public.class)
    public String phoneNumber;
    @JsonProperty("is_block")
    @JsonView(Private.class)
    public boolean is_block;
}
