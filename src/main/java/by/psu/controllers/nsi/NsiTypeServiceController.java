package by.psu.controllers.nsi;

import by.psu.facade.TypeServiceFacade;
import by.psu.model.postgres.TypeService;
import by.psu.service.dto.TypeServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/typeservices")
public class NsiTypeServiceController extends NsiController<TypeService, TypeServiceDTO> {

    @Autowired
    public NsiTypeServiceController(TypeServiceFacade typeServiceFacade) {
        super(typeServiceFacade);
    }

}
