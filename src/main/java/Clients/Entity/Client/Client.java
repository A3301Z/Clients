package Clients.Entity.Client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class Client {
    private UUID id;
    private String firstname;
    private String lastname;
    private String surname;
    private LocalDate birthday;
    private String sex;
    private String email;
    private String phoneNumber;
    private String additionalNumber;
    private boolean isBlock;
    private String reasonOfBlock;

    public Client() {

    }

    public Client(UUID id, String firstname, String lastname, String surname, LocalDate birthday, String sex,
                  String phoneNumber, String email, String additionalNumber, boolean isBlock, String reasonOfBlock) {
        this.id               = id;
        this.firstname        = firstname;
        this.lastname         = lastname;
        this.surname          = surname;
        this.birthday         = birthday;
        this.sex              = sex;
        this.phoneNumber      = phoneNumber;
        this.email            = email;
        this.additionalNumber = additionalNumber;
        this.isBlock          = isBlock;
        this.reasonOfBlock    = reasonOfBlock;
    }

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
    public boolean isBlock() {
        return isBlock;
    }

    @JsonProperty("reasonOfBlock")
    public String getReasonOfBlock() {
        return reasonOfBlock;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdditionalNumber(String additionalNumber) {
        this.additionalNumber = additionalNumber;
    }

    public void setBlock(boolean block) {
        isBlock = block;
    }

    public void setReasonOfBlock(String reasonOfBlock) {
        this.reasonOfBlock = reasonOfBlock;
    }
}
