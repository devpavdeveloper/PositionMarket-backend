package by.psu.service.api;

import by.psu.exceptions.BadRequestException;
import by.psu.exceptions.EntityNotFoundException;
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
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ImageService implements ServiceCRUD<Image> {

    private final RepositoryImage repositoryImage;

    @Value("${load.dir.images}")
    private String dirImages;

    private final Path path = Paths.get(System.getProperty("user.home") + File.separator + dirImages);

    @Autowired
    public ImageService(RepositoryImage repositoryImage) {

        this.repositoryImage = repositoryImage;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Image> getAll() {
        return repositoryImage.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Image> getOne(UUID id) {
        Image image = repositoryImage.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image isn't found by id " + id));
        return Optional.of(image);
    }

    public Boolean isExistsInStore(final Image image) {
        if (isNull(image) || isNull(image.getUrl())) {
            return false;
        }

        return false;
    }

    @Override
    @Transactional
    public Optional<Image> save(Image object) {

        if (nonNull(object.getId())) {
            throw new BadRequestException("Image isn't saved. Id isn't null.");
        }

        return Optional.of(repositoryImage.save(object));
    }

    @Override
    @Transactional
    public Optional<Image> update(Image object) {

        if (nonNull(object.getId())) {
            throw new BadRequestException("Image isn't updated. Id is null.");
        }

        return Optional.of(repositoryImage.save(object));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Optional<Image> optionalImage = repositoryImage.findById(id);
        if ( optionalImage.isPresent() ) {
            Image image = optionalImage.get();

        }
        repositoryImage.deleteById(id);
    }

    @Override
    public void delete(Iterable<UUID> ids) {

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
