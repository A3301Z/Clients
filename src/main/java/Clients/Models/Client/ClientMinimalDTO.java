package Clients.Models.Client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder()
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientMinimalDTO {

    @Schema(description = "Имя")
    public String firstname;

    @Schema(description = "Фамилия")
    public String lastname;

    @Schema(description = "Отчество")
    public String surname;

    @Schema(description = "Дата рождения")
    public LocalDate birthday;

    @Schema(description = "Пол")
    public String sex;

    @Schema(description = "Номер телефона")
    public String phoneNumber;

    @Schema(description = "Статус блокировки")
    public boolean is_block;
}
