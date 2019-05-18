package by.psu.service.api;

import by.psu.exceptions.BadRequestException;
import by.psu.exceptions.EntityNotFoundException;
import by.psu.model.postgres.Image;
import by.psu.model.postgres.repository.RepositoryImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ImageService implements ServiceCRUD<Image> {

    private final RepositoryImage repositoryImage;

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

    public Optional<Image> isExistsInStore(final Image image) {
        if ( isNull(image) ) {
            return Optional.empty();
        }
        // TODO Дописать данный метод как надо
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Image> save(Image object) {

        if ( nonNull(object.getId()) ) {
            throw new BadRequestException("Image isn't saved. Id isn't null.");
        }

        return Optional.of(repositoryImage.save(object));
    }

    @Override
    @Transactional
    public Optional<Image> update(Image object) {

        if ( nonNull(object.getId()) ) {
            throw new BadRequestException("Image isn't updated. Id is null.");
        }

        return Optional.of(repositoryImage.save(object));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        repositoryImage.deleteById(id);
    }

    @Override
    public void delete(Iterable<UUID> ids) {

    }
}
