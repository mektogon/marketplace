package ru.dorofeev.application.ufs.tag.mapper;

import org.mapstruct.Mapper;
import ru.dorofeev.application.ufs.tag.model.web.WebTag;
import ru.dorofeev.database.entity.Tag;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TagMapper {

    public abstract WebTag map(Tag source);

    public abstract List<WebTag> map(Collection<Tag> source);
}
