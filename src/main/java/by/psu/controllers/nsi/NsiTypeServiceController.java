package by.psu.controllers.nsi;

import by.psu.model.postgres.TypeService;
import by.psu.service.dto.NsiDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/typeservices")
public class NsiTypeServiceController extends NsiController<TypeService, NsiDTO> { }
