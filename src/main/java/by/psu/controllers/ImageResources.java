package by.psu.controllers;

import by.psu.service.dto.ImageDTO;
import by.psu.service.facade.ImageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.IOException;

@RestController
@RequestMapping("/api/media/images")
public class ImageResources {

    private final ImageFacade imageFacade;

    @Autowired
    public ImageResources(ImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageDTO> upload(@RequestParam("image") MultipartFile multipart) throws IOException {
        return ResponseEntity.ok(imageFacade.save(multipart));
    }
}
