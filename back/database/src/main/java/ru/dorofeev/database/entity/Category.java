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
 * Таблица с категориями.
 */
@Table(name = "category")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BasicEntityFieldsWithIdGeneration {

    /**
     * Код категории.
     */
    @Column(name = "code")
    private String code;

    /**
     * Наименование категории.
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * Приоритет категории.
     */
    @Column(name = "priority")
    private short priority;

    /**
     * Отображение категории.
     */
    @Column(name = "is_visible")
    private Boolean isVisible;
}
