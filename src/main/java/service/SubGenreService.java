package service;

import org.springframework.stereotype.Service;
import domein.SubGenre;

@Service
public interface SubGenreService {

  public SubGenre getSubGenreById(Integer subGenreId);


}
