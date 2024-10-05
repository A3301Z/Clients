package Clients.Models.Client;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Builder()
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateClientDto {

    @Schema(description = "имя", example = "Иван")
    public String firstname;

    @Schema(description = "Фамилия", example = "Иванов")
    public String lastname;

    @Schema(description = "Отчество", example = "Иванович")
    public String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Schema(description = "Дата рождения", example = "01.12.1990", type = "string", format = "date")
    public LocalDate birthday;

    @Schema(description = "Пол", example = "м/ж")
    public String sex;

    @Schema(description = "Номер телефона", example = "88005553535")
    public String phoneNumber;

    @Schema(description = "Статус блокировки", example = "false")
    public boolean isBlock;
}