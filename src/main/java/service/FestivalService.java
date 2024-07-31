package service;

import org.springframework.stereotype.Service;
import java.util.List;

import domein.Festival;
import domein.NieuwOptreden;
import domein.SubGenre;

@Service
public interface FestivalService {

  public Festival getFestivalById(Integer festivalId);

  public List<Festival> getAllFestivalsByGenreId(Integer genreId);

  public List<Festival> getAllFestivalsByRegioId(Integer regioId);

  public int getBeschikbarePlaatsen(Festival festival);

  public void deleteOptreden(Integer festivalId, Integer optredenId);

  public void addOptreden(Integer festivalId, NieuwOptreden nieuwOptreden, List<Integer> subgenreIds);


  public List<SubGenre> getAllSubGenres(Integer genreId);
}
