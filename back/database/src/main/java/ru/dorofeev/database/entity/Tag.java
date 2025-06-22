package ru.dorofeev.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.database.entity.base.BasicEntityFieldsWithIdGeneration;

/**
 * Таблица с тегами.
 */
@Table(name = "tag")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BasicEntityFieldsWithIdGeneration {

    /**
     * Код тега.
     */
    @Column(name = "code")
    private String code;

    /**
     * Наименование тега.
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * Отображение тега.
     */
    @Column(name = "is_visible")
    private Boolean isVisible;
}
