package by.psu.mappers;

import by.psu.model.postgres.BasicEntity;
import by.psu.service.dto.AbstractDTO;

import java.util.List;

public interface AbstractMapper<T extends BasicEntity, DTO extends AbstractDTO> {

    DTO map(T object);

    T map(DTO object);

    List<DTO> mapToDTOS(List<T> objects);

    List<T> mapToEntities(List<DTO> objects);
}
