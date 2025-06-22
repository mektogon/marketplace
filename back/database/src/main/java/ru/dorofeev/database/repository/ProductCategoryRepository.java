package ru.dorofeev.database.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dorofeev.database.entity.ProductCategory;

import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {

    @Query(value = """
            SELECT pc FROM ProductCategory pc
                JOIN Product p ON p.isVisible = true
                    AND pc.product.id = p.id
                JOIN Category c ON c.isVisible = true
                    AND c.code = :categoryCode
                    AND pc.category.id = c.id
            """
    )
    Slice<ProductCategory> getProductCategoriesByCategoryCode(String categoryCode, Pageable pageable);
}
