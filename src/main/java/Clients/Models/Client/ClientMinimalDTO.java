package Clients.Models.Client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
                    "isBlock",
                    "additionalNumber",
                    "reasonOfBlock",})
public class ClientMinimalDTO {
    public UUID id;
    public String firstname;
    public String lastname;
    public String surname;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate birthday;
    public String sex;
    public String phoneNumber;
    public boolean is_block;
}
