package ru.dorofeev.application.ufs.product.mapper;

import org.mapstruct.Mapper;
import ru.dorofeev.application.ufs.product.model.web.WebProduct;
import ru.dorofeev.application.ufs.tag.mapper.TagMapper;
import ru.dorofeev.database.entity.Product;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", uses = TagMapper.class)
public abstract class ProductMapper {

    public abstract WebProduct map(Product source);

    public abstract List<WebProduct> map(Collection<Product> source);
}
