package by.psu.controllers.nsi;

import by.psu.facade.TypeServiceFacade;
import by.psu.model.postgres.TypeService;
import by.psu.service.dto.TypeServiceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-types")
public class NsiTypeServiceController extends NsiController<TypeService, TypeServiceDTO> {

    public NsiTypeServiceController(TypeServiceFacade typeServiceFacade) {
        super(typeServiceFacade, TypeService.class);
        logger.info("Constructor NsiTypeServiceController init");
    }

    @Override
    public ResponseEntity<TypeServiceDTO> create(@RequestBody TypeServiceDTO obj) {
        return super.create(obj);
    }

    @Override
    public ResponseEntity<TypeServiceDTO> update(@RequestBody TypeServiceDTO obj) {
        return super.update(obj);
    }
    
}
