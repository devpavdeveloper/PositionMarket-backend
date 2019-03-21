package by.psu.service.facade;

import by.psu.model.constants.TypeImage;
import by.psu.model.postgres.Image;
import by.psu.service.api.ImageService;
import by.psu.service.dto.ImageDTO;
import by.psu.service.dto.mappers.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageFacade {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    private final Environment environment;

    @Autowired
    public ImageFacade(ImageService imageService,
                       ImageMapper imageMapper,
                       Environment environment) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
        this.environment = environment;
    }

    @Transactional
    public ImageDTO save(MultipartFile multipartFile) throws IOException {
        byte [] bytes = multipartFile.getBytes();
        Path path = Paths.get("/images/" + multipartFile.getOriginalFilename());
        path = Files.write(path, bytes);
        String port = environment.getProperty("server.port");
        String host = InetAddress.getLoopbackAddress().getHostName();

        Image image = new Image(host + "//:" + port + "/images/" + path.getFileName().toString(), TypeImage.BIG);

        return imageMapper.to(imageService.save(image));
    }


}
