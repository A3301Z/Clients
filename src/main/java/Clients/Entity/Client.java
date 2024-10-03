package Clients.Entity;

import Clients.Models.Client.ClientMinimalDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

    public static Client toClient(ClientMinimalDTO clientMinimalDTO) {
        return Client.builder()
                .id(UUID.randomUUID())
                .firstname(clientMinimalDTO.firstname)
                .lastname(clientMinimalDTO.lastname)
                .surname(clientMinimalDTO.surname)
                .sex(clientMinimalDTO.sex)
                .birthday(clientMinimalDTO.birthday)
                .phoneNumber(clientMinimalDTO.phoneNumber)
                .isBlock(false)
                .build();
    }
}
