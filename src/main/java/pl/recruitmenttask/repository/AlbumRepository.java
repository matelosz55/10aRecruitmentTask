package pl.recruitmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.recruitmenttask.model.Album;
import pl.recruitmenttask.model.Song;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
