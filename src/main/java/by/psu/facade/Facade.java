package by.psu.facade;

import by.psu.service.dto.AbstractDTO;

import java.util.List;

public interface Facade<DTO extends AbstractDTO, ID> {

    public List<DTO> getAll();

    public DTO getOne(ID id);

    public DTO save(DTO object);

    public DTO update(DTO object);

    public void delete(ID id);

    public void delete(List<ID> ids);

}
