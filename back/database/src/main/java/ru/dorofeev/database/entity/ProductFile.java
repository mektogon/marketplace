package ru.dorofeev.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.dorofeev.database.entity.base.BasicEntityFieldsWithIdGeneration;

/**
 * Таблица связей между продуктами и файлами.
 */
@Table(name = "product_file")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductFile extends BasicEntityFieldsWithIdGeneration {

    /**
     * Связанный продукт.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Хеш от файла.
     */
    @Column(name = "file_hash")
    private String fileHash;

    /**
     * Относительный путь расположения до файла, включая наименования и расширение.
     */
    @Column(name = "url")
    private String url;

    /**
     * Миниатюра для файлов типа изображений.
     */
    private Boolean isThumbnail;
}
