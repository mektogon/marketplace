package ru.dorofeev.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.SQLRestriction;
import ru.dorofeev.database.entity.base.BasicEntityFieldsWithIdGeneration;

import java.util.List;

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

    @BatchSize(size = 50)
    @ManyToMany
    @JoinTable(
            name = "product_tag",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @SQLRestriction(value = "is_visible = true")
    private List<Tag> tags;
}
