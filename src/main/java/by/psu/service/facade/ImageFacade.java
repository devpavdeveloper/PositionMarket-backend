package by.psu.service.facade;

import by.psu.exceptions.BadRequestException;
import by.psu.exceptions.ImageNotFoundException;
import by.psu.exceptions.ResourceException;
import by.psu.model.postgres.Image;
import by.psu.service.api.ImageService;
import by.psu.service.dto.ImageDTO;
import by.psu.service.dto.mappers.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ImageFacade {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    private String port;
    private final String host;

    @Value("${load.dir.images}")
    private String dirImages;

    @Autowired
    public ImageFacade(ImageService imageService,
                       ImageMapper imageMapper,
                       Environment environment) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;

        this.port = environment.getProperty("server.port");
        this.host = InetAddress.getLoopbackAddress().getHostName();
    }

    public Resource getFileByName(String name) throws IOException {
        Path path = Paths.get(dirImages);

        if ( !Files.exists(path) ) {
            throw new ImageNotFoundException("Image isn't found with name [" + name + "]");
        }

        Resource resource = new UrlResource(path.resolve(name).normalize().toUri());

        if ( resource.exists() ) {
            return resource;
        } else {
            throw new ImageNotFoundException("Image isn't found with name [" + name + "]");
        }
    }

    public List<ImageDTO> save(MultipartFile[] multipartFile) {
        return Stream.of(multipartFile)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Transactional
    public ImageDTO save(MultipartFile multipartFile) {
        Path path = Paths.get(dirImages);

        Image image = new Image();
        image.setUrl("/api/images/" + multipartFile.getOriginalFilename());

        try {
            image = imageService.save(image);
        } catch (Exception ex) {
            throw new BadRequestException("Error occurred while saving the image", ex);
        }

        if ( !Files.exists(path) ) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new ResourceException("Resource isn't loaded, because server doesn't have a image directory.", e);
            }
        }

        try {
            Files.write(path.resolve(multipartFile.getOriginalFilename()), multipartFile.getBytes());
        } catch (IOException e) {
            throw new ResourceException("Resource isn't loaded, because an error occurred while writing the file", e);
        }

        return imageMapper.to(image);
    }

}
