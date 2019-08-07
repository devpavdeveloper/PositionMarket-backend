package by.psu.facade;

import by.psu.mappers.nsi.NsiMapper;
import by.psu.model.postgres.Nsi;
import by.psu.service.api.NsiService;
import by.psu.service.dto.NsiDTO;

public abstract class NsiFacade <T extends Nsi, E extends NsiDTO>
        extends AbstractFacade<T, E> {

    protected final NsiService<T> nsiService;
    protected final NsiMapper <T, E> mapper;

    public NsiFacade(NsiService<T> nsiService, NsiMapper<T, E> mapper) {
        super(nsiService, mapper);
        this.nsiService = nsiService;
        this.mapper = mapper;
    }

}
