package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domein.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Integer> {

  public List<Festival> findAllByGenreId(Integer genreId);

  public List<Festival> findAllByRegioId(Integer regioId);

}
