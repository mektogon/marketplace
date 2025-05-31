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
 * Таблица с продуктами.
 */
@Table(name = "product")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BasicEntityFieldsWithIdGeneration {

    /**
     * Наименование продукта.
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * Описание продукта.
     */
    @Column(name = "description")
    private String description;

    /**
     * Отображение продукта.
     */
    @Column(name = "is_visible")
    private Boolean isVisible;
}
