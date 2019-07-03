package by.psu.facade;

import by.psu.exceptions.BadRequestException;
import by.psu.exceptions.ImageNotFoundException;
import by.psu.exceptions.ResourceException;
import by.psu.mappers.ImageMapper;
import by.psu.model.postgres.Image;
import by.psu.service.api.ImageService;
import by.psu.service.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ImageFacade {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    @Value("${load.dir.images}")
    private String dirImages;

    @Autowired
    public ImageFacade(ImageService imageService,
                       ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    public Resource getFileByName(String name) throws IOException {
        Path path = Paths.get(System.getProperty("user.home") + File.separator + dirImages);

        if (Files.notExists(path.resolve(name).normalize())) {
            throw new ImageNotFoundException("Image isn't found with name [" + name + "]");
        }

        Resource resource = new UrlResource(path.resolve(name).normalize().toUri());

        if (resource.exists()) {
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
        Path path = Paths.get(System.getProperty("user.home") + File.separator + dirImages);

        Image image = new Image();
        String nameImage = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        image.setUrl("/api/images/" + nameImage);
        image.setName(nameImage);
        image.setLength(multipartFile.getSize());

        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new ResourceException("Resource isn't loaded, because server doesn't have a image directory.", e);
            }
        }

        try {
            List<Image> files = imageService.findByLength(multipartFile.getSize());

            for (Image imageStore : files) {
                Optional<File> optionalFileStore = imageService.convertImageToFile(imageStore);
                if (optionalFileStore.isPresent()) {
                    InputStream inputStreamStoreFile = new FileInputStream(optionalFileStore.get());
                    InputStream inputStreamMultipartFile = new ByteArrayInputStream(multipartFile.getBytes());
                    boolean equals = imageService.compareImage(inputStreamStoreFile, inputStreamMultipartFile);
                    if (equals) {
                        return imageMapper.map(image);
                    }
                }
            }
            Files.write(path.resolve(nameImage), multipartFile.getBytes());
        } catch (IOException e) {
            throw new ResourceException("Resource isn't loaded, because an error occurred while writing the file", e);
        }

        try {
            image = imageService.save(image).get();
        } catch (Exception ex) {
            throw new BadRequestException("Error occurred while saving the image", ex);
        }

        return imageMapper.map(image);
    }



}
