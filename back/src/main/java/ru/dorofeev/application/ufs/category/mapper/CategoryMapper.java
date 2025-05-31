package ru.dorofeev.application.ufs.category.mapper;

import org.mapstruct.Mapper;
import ru.dorofeev.application.ufs.category.model.web.WebCategory;
import ru.dorofeev.database.entity.Category;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public abstract WebCategory map(Category source);

    public abstract List<WebCategory> map(Collection<Category> source);
}
