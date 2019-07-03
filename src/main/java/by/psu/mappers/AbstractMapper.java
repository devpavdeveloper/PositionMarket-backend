package by.psu.mappers;

import by.psu.model.postgres.BasicEntity;

public interface AbstractMapper<T extends BasicEntity, DTO> {

    DTO map(T object);

    T map(DTO object);

}
