package ru.dorofeev.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dorofeev.database.entity.Category;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
