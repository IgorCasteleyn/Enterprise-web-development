package service;

import org.springframework.stereotype.Service;
import java.util.List;

import domein.Festival;

@Service
public interface FestivalService {

  public Festival getFestivalById(Integer festivalId);

  public List<Festival> getAllFestivalsByGenreId(Integer genreId);

  public List<Festival> getAllFestivalsByRegioId(Integer regioId);

  public int getBeschikbarePlaatsen(Festival festival);

  public void deleteOptreden(Integer festivalId, Integer optredenId);

  // public void addOptreden(Integer festivalId, String naam, Integer podiumId,
  // LocalTime aanvangsuur);

  public void saveFestival(Festival festival);
}
