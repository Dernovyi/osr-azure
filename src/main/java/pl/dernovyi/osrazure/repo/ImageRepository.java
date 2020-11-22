package pl.dernovyi.osrazure.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.dernovyi.osrazure.model.ImageDto;

@Repository
public interface ImageRepository extends CrudRepository<ImageDto , Long> {
    boolean existsPictureByUrl(String url);
}
