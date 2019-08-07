package by.psu.mappers.nsi;

import by.psu.mappers.AbstractMapper;
import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;


public interface NsiMapper <T extends Nsi, DTO extends NsiDTO>
        extends AbstractMapper<T, DTO> {

    @Mapping(source = "title.values", target = "values")
    DTO map(T nsi);

    @InheritInverseConfiguration
    T map(DTO nsi);

    default T fromId(UUID id) {
        if (id == null) {
            return null;
        }
        Class<T> object = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T newInstance = null;
        try {
            newInstance = object.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if (newInstance != null) {
            newInstance.setId(id);
        }
        return newInstance;
    }
}
