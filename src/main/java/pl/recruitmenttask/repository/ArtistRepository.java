package pl.recruitmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.recruitmenttask.model.Artist;
import pl.recruitmenttask.model.Song;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
