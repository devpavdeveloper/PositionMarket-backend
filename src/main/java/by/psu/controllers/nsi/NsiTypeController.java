package by.psu.controllers.nsi;

import by.psu.model.postgres.TypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/types")
public class NsiTypeController extends NsiController<TypeService> {}
