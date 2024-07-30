package service;

import org.springframework.stereotype.Service;
import java.util.List;

import domein.Festival;


@Service
public interface FestivalService {

  public List<Festival> getAllFestivalsByGenre(Integer genreId);

  public int getBeschikbarePlaatsen(Festival festival);
}
