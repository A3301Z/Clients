package Clients.Entity.Client;

import Clients.Entity.Goal.Goal;
import Clients.Models.Client.ClientDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@JsonPropertyOrder({"id",
        "lastname",
        "firstname",
        "surname",
        "sex",
        "birthday",
        "phoneNumber",
        "email",
        "additionalNumber",
        "isBlock",
        "reasonOfBlock"})
@Data
@Builder
@RequiredArgsConstructor
public class Client implements Serializable {
    @Id()
    private UUID id;
    private String lastname;
    private String firstname;
    private String surname;
    private LocalDate birthday;
    private String sex;
    private String email;
    private String phoneNumber;
    private String additionalNumber;
    private boolean isBlock;
    private String reasonOfBlock;
    @Lob
    private byte[] content;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Goal> goals;

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("surname")
    public String getSurname() {
        return surname;
    }

    @JsonProperty("birthday")
    @JsonFormat(pattern = "dd.MM.yyyy")
    public LocalDate getBirthday() {
        return birthday;
    }

    @JsonProperty("sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("additionalNumber")
    public String getAdditionalNumber() {
        return additionalNumber;
    }

    @JsonProperty("isBlock")
    public boolean getBlockStatus() {
        return isBlock;
    }

    @JsonProperty("reasonOfBlock")
    public String getReasonOfBlock() {
        return reasonOfBlock;
    }

    @Lob
    @JsonIgnore
    @JsonProperty("content")
    public byte[] getContent() {
        return content;
    }

    public static Client toClient(ClientDto clientDto) {
        return Client.builder()
                .id(UUID.randomUUID())
                .firstname(clientDto.firstname)
                .lastname(clientDto.lastname)
                .surname(clientDto.surname)
                .sex(clientDto.sex)
                .birthday(clientDto.birthday)
                .phoneNumber(clientDto.phoneNumber)
                .isBlock(false)
                .additionalNumber(clientDto.additionalNumber)
                .email(clientDto.email)
                .reasonOfBlock(null)
                .build();
    }
}
