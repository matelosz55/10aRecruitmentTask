package pl.recruitmenttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.recruitmenttask.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
}
