package Clients.Models.Client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Dto клиента")
public class ClientDto {

    @Schema(description = "Уникальный идентификатор")
    public UUID id;

    @Schema(description = "имя")
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
    public boolean isBlock;

    @Schema(description = "Почта")
    public String email;

    @Schema(description = "Дополнительный номер телефона")
    public String additionalNumber;

    @Schema(description = "Причина блокировки")
    public String reasonOfBlock;
}





