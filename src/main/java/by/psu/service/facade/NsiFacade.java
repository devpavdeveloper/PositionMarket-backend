package by.psu.service.facade;

import by.psu.model.postgres.Nsi;
import by.psu.service.api.ServiceNsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.mappers.nsi.NsiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class NsiFacade <T extends Nsi, E extends NsiDTO> {

    protected final ServiceNsi<T> serviceNsi;

    protected NsiMapper <T, E> mapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public NsiFacade(ServiceNsi<T> serviceNsi, NsiMapper<T, E> mapper) {
        this.serviceNsi = serviceNsi;
        this.mapper = mapper;
    }

    public List<E> getAll() {
        return serviceNsi.getAll().stream()
                .map(nsi -> mapper.to(nsi))
                .collect(Collectors.toList());
    }

    public E getOne(@PathVariable UUID id) {
        return mapper.to(serviceNsi.getOne(id));
    }

    public E save(E nsiDTO) {
        return mapper.to(serviceNsi.save(mapper.from(nsiDTO)));
    }

    public E update(E nsiDTO) {
        return mapper.to(serviceNsi.update(mapper.from(nsiDTO)));
    }
}
