package pl.recruitmenttask.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.recruitmenttask.model.Artist;
import pl.recruitmenttask.repository.ArtistRepository;

import java.util.Optional;

public class ArtistConverter implements Converter<String, Optional<Artist>> {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Optional<Artist> convert(String source) {
        return artistRepository.findById(Long.parseLong(source));
    }
}