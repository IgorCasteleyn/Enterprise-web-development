package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domein.SubGenre;
import repository.SubGenreRepository;

@Service("subGenreService")
public class SubGenreServiceImpl implements SubGenreService {

  @Autowired
  private SubGenreRepository subGenreRepository;

  @Override
  public SubGenre getSubGenreById(Integer subGenreId) {
    return subGenreRepository.findById(subGenreId).orElse(null);
  }

}
