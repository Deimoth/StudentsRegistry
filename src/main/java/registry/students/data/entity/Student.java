package registry.students.data.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "STUDENT")
public class Student {

    public Student() {

    }

    /**
     * Уникальный номер
     */
    @Id
    @NotNull
    @Column(name = "NUMBER")
    private String number;

    /**
     * Имя
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Фамилия
     */
    @Column(name = "SURNAME")
    private String surname;

    /**
     * Отчество
     */
    @Column(name = "SECOND_NAME")
    private String secondName;

    /**
     * Дата рождения
     */
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDay;

    /**
     * Группа
     */
    @Column(name = "GROUP_NAME")
    private String groupName;

}
