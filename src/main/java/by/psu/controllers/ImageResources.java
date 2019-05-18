package by.psu.controllers;

import by.psu.controllers.exc_handler.ExceptionDataHandler;
import by.psu.service.dto.ImageDTO;
import by.psu.service.facade.ImageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/images")
public class ImageResources {

    private final Logger logger = Logger.getLogger(ExceptionDataHandler.class.getName());

    private final ImageFacade imageFacade;

    @Autowired
    public ImageResources(ImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }

    @PostMapping(value = "/upload", produces = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity upload(@RequestParam("image") MultipartFile multipart) throws IOException {
        return ResponseEntity.ok(imageFacade.save(multipart));
    }

    @PostMapping(value = "/multiple/upload", produces = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<ImageDTO>> upload(@RequestParam("image") MultipartFile[] multiparts) throws IOException {
        return ResponseEntity.ok(imageFacade.save(multiparts));
    }

    @GetMapping("/{name:.+}")
    public ResponseEntity<Resource> getImages(@PathVariable String name, HttpServletRequest request) throws IOException {
        Resource resource = imageFacade.getFileByName(name);

        String contentType = "application/octet-stream";

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(resource.getFile().length())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
