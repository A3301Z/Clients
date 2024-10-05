package Clients.Entity;

import Clients.Models.Client.ClientDto;
import Clients.Models.Client.CreateClientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client implements Serializable {

    /**
     *  Уникальный идентификатор
     */
    @Id
    private UUID id;

    /**
     * Фамилия
     */
    @Column("lastname")
    private String lastname;

    /**
     * Имя
     */
    @Column("firstname")
    private String firstname;

    /**
     * Отчество
     */
    @Column("surname")
    private String surname;

    /**
     * Дата рождения
     */
    @Column("birthday")
    private LocalDate birthday;

    /**
     * Пол
     */
    @Column("sex")
    private String sex;

    /**
     * Почта
     */
    @Column("email")
    private String email;

    /**
     * Номер телефона
     */
    @Column("phone_number")
    private String phoneNumber;

    /**
     * Дополнительный номер телефона
     */
    @Column("additional_number")
    private String additionalNumber;

    /**
     * Статус блокировки
     */
    @Column("is_block")
    private boolean isBlock;

    /**
     * Причина блокировки
     */
    @Column("reason_of_block")
    private String reasonOfBlock;

    /**
     * Фото клиента
     */
    @Column("content")
    private byte[] content;

    public static Client toClient(CreateClientDto createClientDto) {
        return Client.builder()
                .firstname(createClientDto.firstname)
                .lastname(createClientDto.lastname)
                .surname(createClientDto.surname)
                .sex(createClientDto.sex)
                .birthday(createClientDto.birthday)
                .phoneNumber(createClientDto.phoneNumber)
                .isBlock(false)
                .build();
    }

    public static Client toClient(ClientDto clientDto) {
        return Client.builder()
                .id(clientDto.id)
                .firstname(clientDto.firstname)
                .lastname(clientDto.lastname)
                .surname(clientDto.surname)
                .birthday(clientDto.birthday)
                .sex(clientDto.sex)
                .phoneNumber(clientDto.phoneNumber)
                .additionalNumber(clientDto.additionalNumber)
                .email(clientDto.email)
                .isBlock(clientDto.isBlock)
                .reasonOfBlock(clientDto.reasonOfBlock)
                .build();
    }
}
