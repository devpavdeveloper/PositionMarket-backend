package by.psu.service.facade;

import by.psu.model.postgres.Nsi;
import by.psu.service.api2.ServiceNsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.mappers.NsiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NsiFacade<T extends Nsi> {

    @Autowired
    protected ServiceNsi<T> serviceNsi;

    protected NsiMapper nsiMapper = NsiMapper.INSTANCE;

    public List<NsiDTO> getAll() {
        return serviceNsi.getAll().stream().map(nsi -> nsiMapper.to(nsi)).collect(Collectors.toList());
    }
}
