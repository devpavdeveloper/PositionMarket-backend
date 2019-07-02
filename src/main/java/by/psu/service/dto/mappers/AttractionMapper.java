package by.psu.service.dto.mappers;

import by.psu.model.postgres.*;
import by.psu.service.api.TagService;
import by.psu.service.api.TypeService;
import by.psu.service.api.TypeServiceService;
import by.psu.service.dto.AttractionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Mapper(componentModel = "spring", uses = {
        ProductMapper.class,
        PositionImageMapper.class
}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AttractionMapper {

    @Autowired
    protected TypeService typeService;
    @Autowired
    protected TagService tagService;
    @Autowired
    protected TypeServiceService typeServiceService;

    @Mapping(source = "title.values", target = "title")
    @Mapping(source = "description.values", target = "description")
    @Mapping(target = "tags", expression = "java( convertToString(nsi.getTags()) )")
    @Mapping(target = "types", expression = "java( convertToString(nsi.getTypes()) )")
    @Mapping(source = "images", target = "images")
    public abstract AttractionDTO map(Attraction nsi);

    @Mapping(source = "title", target = "title.values")
    @Mapping(source = "description", target = "description.values")
    @Mapping(target = "tags", expression = "java( convertToTag(nsi.getTags()) )")
    @Mapping(target = "types", expression = "java( convertToType(nsi.getTypes()) )")
    @Mapping(source = "images", target = "images")
    public abstract Attraction map(AttractionDTO nsi);

    protected List<UUID> convertToString(List<? extends Nsi> collection) {
        if (nonNull(collection) && !collection.isEmpty()) {
            return collection.stream()
                    .filter(Objects::nonNull)
                    .filter(nsi -> nonNull(nsi.getId()))
                    .map(BasicEntity::getId)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    protected List<Tag> convertToTag(List<? extends UUID> collection) {
        return collection.stream().map(uuid -> tagService.getOne(uuid)).collect(Collectors.toList());
    }

    protected List<Type> convertToType(List<? extends UUID> collection) {
        return collection.stream().map(uuid -> typeService.getOne(uuid)).collect(Collectors.toList());
    }

}
