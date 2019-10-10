package by.psu.service.api;

import by.psu.model.postgres.Image;
import by.psu.model.postgres.repository.RepositoryImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ImageService extends AbstractService<Image> {

    private final RepositoryImage repositoryImage;

    @Value("${load.dir.images}")
    private String dirImages;

    private final Path path = Paths.get(System.getProperty("user.home") + File.separator + dirImages);

    @Autowired
    public ImageService(RepositoryImage repositoryImage) {
        super(repositoryImage, Image.class);
        this.repositoryImage = repositoryImage;
    }

    @Transactional
    public List<Image> findByLength(long length) {
        List<Image> images = repositoryImage.findAllByLength(length);

        if (isNull(images) || images.isEmpty()) {
            return Collections.emptyList();
        }

        return images;
    }

    @Transactional
    public List<File> convertImageModelListToListFile(List<? extends Image> collection) {
        return collection.stream()
                .map(this::convertImageToFile)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<File> convertImageToFile(Image image) {
        Path file = path.resolve(Paths.get(image.getName()));
        boolean existImage = Files.exists(file);

        if (existImage) {
            return Optional.of(file.toFile());
        }

        return Optional.empty();
    }

    public boolean compareImage(InputStream storeImage, InputStream image) {

        try {
            BufferedImage biA = ImageIO.read(storeImage);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(image);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();

            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to compare image files ..." + e);
            return false;
        }
    }
}
