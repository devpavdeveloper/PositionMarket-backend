package by.psu.mappers;

import by.psu.mappers.nsi.TagNsiMapper;
import by.psu.mappers.nsi.TypeNsiMapper;
import by.psu.model.postgres.*;
import by.psu.service.dto.PositionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        TagNsiMapper.class,
        TypeNsiMapper.class,
        ProductMapper.class,
        PositionImageMapper.class
}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PositionMapper extends AbstractMapper<Position, PositionDTO> {

    @Mapping(source = "title.values", target = "title")
    @Mapping(source = "description.values", target = "description")
    @Mapping(target = "tags", expression = "java(convertListNsiToListId(position.getTags()))")
    @Mapping(target = "types", expression = "java(convertListNsiToListId(position.getTypes()))")
    @Mapping(source = "images", target = "images")
    PositionDTO map(Position position);

    @Mapping(source = "title", target = "title.values")
    @Mapping(source = "description", target = "description.values")
    @Mapping(source = "images", target = "images")
    @Mapping(target = "tags", expression = "java(getTags(positionDTO.getTags()))")
    @Mapping(target = "types", expression = "java(getTypes(positionDTO.getTypes()))")
    Position map(PositionDTO positionDTO);

    default List<Tag> getTags(List<UUID> uuids) {
        return getNsiFromListUUID(uuids, (id) -> {
            Tag tag = new Tag();
            tag.setId(id);
            return tag;
        });
    }

    default List<Type> getTypes(List<UUID> uuids) {
        return getNsiFromListUUID(uuids, (id) -> {
            Type type = new Type();
            type.setId(id);
            return type;
        });
    }

    default <T> List<T> getNsiFromListUUID(List<UUID> nsis, Function<UUID, T> converter) {
        if (nsis == null || nsis.isEmpty()) {
            return Collections.emptyList();
        }

        return nsis.stream()
                .map(converter)
                .collect(Collectors.toList());
    }

    default List<UUID> convertListNsiToListId(List<? extends Nsi> nsis) {
        if (nsis == null || nsis.isEmpty()) {
            return Collections.emptyList();
        }

        return nsis.stream()
                .map(BasicEntity::getId)
                .collect(Collectors.toList());
    }

}
