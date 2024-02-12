package Clients.Models.Client;

import Clients.Models.Views.Private;
import Clients.Models.Views.Public;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;

@JsonPropertyOrder({"id",
                    "lastname",
                    "firstname",
                    "surname",
                    "sex",
                    "birthday",
                    "phoneNumber",
                    "email",
                    "additionalNumber"})
public class ClientDTO extends ClientMinimalDTO {
    @JsonView(Public.class)
    @JsonProperty("email")
    public String email;
    @JsonView(Public.class)
    @JsonProperty("additionalNumber")
    public String additionalNumber;
    @JsonView(Private.class)
    @JsonProperty("reasonOfBlock")
    public String reasonOfBlock;
}
