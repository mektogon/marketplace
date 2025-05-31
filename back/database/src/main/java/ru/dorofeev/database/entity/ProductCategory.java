package ru.dorofeev.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.database.entity.base.BasicEntityFieldsWithIdGeneration;

/**
 * Таблица связей между продуктами и категориями.
 */
@Table(name = "product_category")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BasicEntityFieldsWithIdGeneration {

    /**
     * Связанный продукт.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Связанная категория.
     */
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
