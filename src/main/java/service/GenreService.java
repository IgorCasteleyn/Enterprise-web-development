package service;

import org.springframework.stereotype.Service;
import java.util.List;
import domein.Genre;

@Service
public interface GenreService {

  public List<Genre> getAllGenres();

  public Genre getGenreById(Integer genreId);

}
