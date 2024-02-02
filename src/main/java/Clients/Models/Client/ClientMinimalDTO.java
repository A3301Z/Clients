package Clients.Models.Client;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public class ClientMinimalDTO {
    public UUID id;
    public String firstname;
    public String lastname;
    public String surname;
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate birthday;
    public String sex;
    public String phoneNumber;
    public String email;
    public String additionalNumber;
    public boolean is_block;
    public String reasonOfBlock;
}
