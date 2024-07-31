package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domein.Genre;
import domein.SubGenre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

  SubGenre getSubGenreById(Integer subGenreId);

}
