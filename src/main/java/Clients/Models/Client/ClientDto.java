package Clients.Models.Client;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Schema(description = "Уникальный идентификатор", example = "Some UUID")
    public UUID id;

    @Schema(description = "имя", example = "Иван")
    public String firstname;

    @Schema(description = "Фамилия", example = "Иванов")
    public String lastname;

    @Schema(description = "Отчество", example = "Иванович")
    public String surname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    @Schema(description = "Дата рождения", example = "01.12.1990")
    public LocalDate birthday;

    @Schema(description = "Пол", example = "м/ж")
    public String sex;

    @Schema(description = "Номер телефона", example = "88005553535")
    public String phoneNumber;

    @Schema(description = "Статус блокировки", example = "true/false")
    public boolean isBlock;

    @Schema(description = "Почта", example = "ivanov@mail.com")
    public String email;

    @Schema(description = "Дополнительный номер телефона", example = "88005553535")
    public String additionalNumber;

    @Schema(description = "Причина блокировки", example = "Подрался с тренером")
    public String reasonOfBlock;
}





